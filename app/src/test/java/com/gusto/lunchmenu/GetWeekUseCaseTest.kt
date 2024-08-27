package com.gusto.lunchmenu

import com.gusto.lunchmenu.domain.usecase.GetWeekUseCase
import org.junit.Test

import org.junit.Assert.*
import java.time.LocalDate

class GetWeekUseCaseTest {
    @Test
    fun testFirstWeek() {
        val useCase = GetWeekUseCase()
        assertEquals(1, useCase.invoke(LocalDate.of(2024, 1, 1)))
    }

    @Test
    fun testLastWeek() {
        val useCase = GetWeekUseCase()
        assertEquals(53, useCase.invoke(LocalDate.of(2024, 12, 31)))
    }
}