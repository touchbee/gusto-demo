package com.gusto.lunchmenu.data

import com.gusto.lunchmenu.data.common.DataState
import com.gusto.lunchmenu.data.model.LunchMenu
import com.gusto.lunchmenu.data.model.LunchMenuSchedule
import com.gusto.lunchmenu.data.model.LunchMenuWeek
import com.gusto.lunchmenu.data.model.RemoteLunchMenuItem
import com.gusto.lunchmenu.data.model.toLunchMenuItem
import com.gusto.lunchmenu.di.DefaultDispatcher
import com.gusto.lunchmenu.domain.repository.LunchMenuRepositoryInterface
import com.gusto.lunchmenu.domain.usecase.GetCurrentWeekUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import java.time.LocalDate
import javax.inject.Inject

/**
 * Implementation of the [LunchMenuRepositoryInterface]
 */
class LunchMenuRepository @Inject constructor(
    private val dataSource: LunchMenuDataSource,
    private val currentWeekUseCase: GetCurrentWeekUseCase,
    @DefaultDispatcher coroutineDispatcher: CoroutineDispatcher
): LunchMenuRepositoryInterface {

    private val scope = CoroutineScope(coroutineDispatcher)

    override val menuSchedule = MutableStateFlow<DataState<LunchMenuSchedule>>(
        DataState.Loading()
    )

    override fun refresh() {
        menuSchedule.value = DataState.Loading()

        scope.launch {
            try {
                withTimeout(REFRESH_TIMEOUT_MILLIS) {
                    menuSchedule.value = DataState.Success(
                        dataSource.getLunchMenu().toLunchMenuSchedule(
                            currentWeekUseCase()
                        )
                    )
                }
            } catch (e: Exception) {
                menuSchedule.value = DataState.Error(e)
            }
        }
    }

    companion object {
        private const val REFRESH_TIMEOUT_MILLIS = 5_000L
    }
}

/**
 * For this demo I'll supply the startWeek when creating the data model.
 * In a real app week/year should be coming from the web server with the menu payload
 */
fun List<List<RemoteLunchMenuItem>>.toLunchMenuSchedule(startWeek: Int): LunchMenuSchedule {

    val mapOfWeekToMenu = mutableListOf<LunchMenuWeek>()

    var year = LocalDate.now().year
    var currentStartWeek = startWeek

    forEachIndexed { index, lunchMenuItems ->

        var week = currentStartWeek + index
        if (week > 52) {
            week -= 52
            currentStartWeek = 0
            year++
        }

        mapOfWeekToMenu.add(
            LunchMenuWeek(
                week,
                year,
                LunchMenu(
                    itemMonday = lunchMenuItems[0].toLunchMenuItem(),
                    itemTuesday = lunchMenuItems[1].toLunchMenuItem(),
                    itemWednesday = lunchMenuItems[2].toLunchMenuItem(),
                    itemThursday = lunchMenuItems[3].toLunchMenuItem(),
                    itemFriday = lunchMenuItems[4].toLunchMenuItem(),
                )
            )
        )
    }

    return LunchMenuSchedule(
        mapOfWeekToMenu
    )
}