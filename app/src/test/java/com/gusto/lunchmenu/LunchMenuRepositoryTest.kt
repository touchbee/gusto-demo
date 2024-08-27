package com.gusto.lunchmenu

import com.gusto.lunchmenu.data.LunchMenuDataSource
import com.gusto.lunchmenu.data.LunchMenuRepository
import com.gusto.lunchmenu.data.common.DataState
import com.gusto.lunchmenu.data.model.LunchMenuSchedule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class LunchMenuRepositoryTest {

    @Test
    fun `when LunchMenuRepository is created, then menuSchedule returns Loading` () = runTest {
        val dispatcher = StandardTestDispatcher(testScheduler)

        val repository = LunchMenuRepository(
            mockk(relaxed = true),
            mockk(relaxed = true),
            dispatcher
        )

        assertTrue(repository.menuSchedule.value is DataState.Loading)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when LunchMenuRepository refresh is called with success, then menuSchedule emits correct values` () = runTest {
        val dispatcher = StandardTestDispatcher(testScheduler)

        val dataSource = mockk<LunchMenuDataSource>()
        coEvery { dataSource.getLunchMenu() } returns mockk(relaxed = true)

        val repository = LunchMenuRepository(
            dataSource,
            mockk(relaxed = true),
            dispatcher
        )

        val events = mutableListOf<DataState<LunchMenuSchedule>>()

        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            repository.menuSchedule.toList(events)
        }

        repository.refresh()

        delay(1)

        assertTrue(events[events.size - 2] is DataState.Loading)
        assertTrue(events.last() is DataState.Success)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when LunchMenuRepository refresh is called with error, then menuSchedule emits correct values` () = runTest {
        val dispatcher = StandardTestDispatcher(testScheduler)

        val dataSource = mockk<LunchMenuDataSource>()
        coEvery { dataSource.getLunchMenu() } throws Exception()

        val repository = LunchMenuRepository(
            dataSource,
            mockk(relaxed = true),
            dispatcher
        )

        val events = mutableListOf<DataState<LunchMenuSchedule>>()

        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            repository.menuSchedule.toList(events)
        }

        repository.refresh()

        delay(1)

        assertTrue(events[events.size - 2] is DataState.Loading)
        assertTrue(events.last() is DataState.Error)
    }
}