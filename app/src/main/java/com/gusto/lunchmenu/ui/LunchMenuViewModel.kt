package com.gusto.lunchmenu.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gusto.lunchmenu.data.common.DataState
import com.gusto.lunchmenu.data.model.LunchMenuSchedule
import com.gusto.lunchmenu.domain.repository.LunchMenuRepositoryInterface
import com.gusto.lunchmenu.domain.usecase.GetCurrentWeekDayUseCase
import com.gusto.lunchmenu.domain.usecase.GetCurrentWeekUseCase
import com.gusto.lunchmenu.domain.usecase.GetFormattedTitleDateUseCase
import com.gusto.lunchmenu.domain.usecase.GetMenuForWeekUseCase
import com.gusto.lunchmenu.domain.usecase.GetNextWeekUseCase
import com.gusto.lunchmenu.domain.usecase.GetPreviousWeekUseCase
import com.gusto.lunchmenu.domain.usecase.GetWeekStartDateUseCase
import com.gusto.lunchmenu.domain.usecase.GetWeekUseCase
import com.gusto.lunchmenu.domain.usecase.IsThisWeekUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class LunchMenuViewModel @Inject constructor(
    private val repository: LunchMenuRepositoryInterface,
    private val getMenuForWeekUseCase: GetMenuForWeekUseCase,
    private val getFormattedTitleDateUseCase: GetFormattedTitleDateUseCase,
    private val getPreviousWeekUseCase: GetPreviousWeekUseCase,
    private val getNextWeekUseCase: GetNextWeekUseCase,
    private val getCurrentWeekDayUseCase: GetCurrentWeekDayUseCase,
    private val isThisWeekUseCase: IsThisWeekUseCase,
    private val getWeekStartDateUseCase: GetWeekStartDateUseCase,
    private val getWeekUseCase: GetWeekUseCase
): ViewModel() {

    private var currentDate by mutableStateOf(LocalDate.now())

    private var _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        observeMenuSchedule()
        refreshData()
    }

    private fun observeMenuSchedule() {
        repository.menuSchedule.onEach { state ->
            when(state) {
                is DataState.Loading -> {
                    _uiState.value = UiState.Loading
                }
                is DataState.Error -> {
                    _uiState.value = UiState.Error(state.error)
                }
                is DataState.Success -> {
                    _uiState.value = getUiStateForSchedule(state.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun nextWeek() {
        val schedule = lunchMenuSchedule ?: return

         currentDate = getNextWeekUseCase(currentDate)
        _uiState.value = getUiStateForSchedule(schedule)
    }

    fun previousWeek() {
        val schedule = lunchMenuSchedule ?: return

        currentDate = getPreviousWeekUseCase(currentDate)
        _uiState.value = getUiStateForSchedule(schedule)
    }

    private fun getUiStateForSchedule(schedule: LunchMenuSchedule): UiState {

        val currentWeek = getWeekUseCase(currentDate)
        return getMenuForWeekUseCase(currentWeek, schedule)?.let { menuForWeek ->
            UiState.Success(
                LunchMenuUiState(
                    currentWeek = currentWeek,
                    weekDay = getCurrentWeekDayUseCase(),
                    menu = menuForWeek
                )
            )
        } ?: kotlin.run {
            UiState.Error(
                RuntimeException("Couldn't find menu for week")
            )
        }
    }

    fun retry() {
        refreshData()
    }

    private fun refreshData() {
        repository.refresh()
    }

    val formattedTitleDate: MutableState<String> get() {
        return mutableStateOf(
            getFormattedTitleDateUseCase(
                getWeekStartDateUseCase(currentDate)
            )
        )
    }

    val isThisWeek: MutableState<Boolean> get() {
        return mutableStateOf(
            isThisWeekUseCase(getWeekUseCase(currentDate))
        )
    }

    private val lunchMenuSchedule: LunchMenuSchedule? get() {
        return (repository.menuSchedule.value as? DataState.Success<LunchMenuSchedule>)?.data
    }
}