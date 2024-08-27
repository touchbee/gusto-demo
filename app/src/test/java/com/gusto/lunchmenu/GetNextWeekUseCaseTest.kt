package com.gusto.lunchmenu

import com.gusto.lunchmenu.data.model.LunchMenu
import com.gusto.lunchmenu.data.model.LunchMenuSchedule
import com.gusto.lunchmenu.data.model.LunchMenuWeek
import com.gusto.lunchmenu.domain.usecase.GetNextWeekUseCase
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test

class GetNextWeekUseCaseTest {
    @Test
    fun `when schedule contains single week 1, GetNextWeekUseCase() returns null`() {
        val schedule = LunchMenuSchedule(
            listOf(
                LunchMenuWeek(
                    week = 1,
                    year = 2024,
                    LunchMenu(
                        "A",
                        "B",
                        "C",
                        "D",
                        "E"
                    )
                )
            )
        )

        val useCase = GetNextWeekUseCase()
        assertNull(useCase.invoke(1, schedule))
    }

    @Test
    fun `when schedule doesn't contain specified week, GetNextWeekUseCase() returns null`() {
        val schedule = LunchMenuSchedule(
            listOf(
                LunchMenuWeek(
                    week = 1,
                    year = 2024,
                    LunchMenu(
                        "A",
                        "B",
                        "C",
                        "D",
                        "E"
                    )
                )
            )
        )

        val useCase = GetNextWeekUseCase()
        assertNull(useCase.invoke(5, schedule))
    }


    @Test
    fun `when schedule contains single weeks 1 and 2, GetNextWeekUseCase() returns week 2`() {
        val schedule = LunchMenuSchedule(
            listOf(
                LunchMenuWeek(
                    week = 1,
                    year = 2024,
                    mockk()
                ),
                LunchMenuWeek(
                    week = 2,
                    year = 2024,
                    mockk()
                )
            )
        )

        val useCase = GetNextWeekUseCase()
        val result = useCase.invoke(1, schedule)

        assertNotNull(result)
        assertEquals(2, result?.first)
        assertEquals(2024, result?.second)
    }
}