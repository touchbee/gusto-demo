package com.gusto.lunchmenu.domain.usecase

import java.time.LocalDate

class GetNextWeekUseCase {

    /**
     * @return A [Pair] of week and year or null
     */
    operator fun invoke(date: LocalDate): LocalDate {
        return date.plusWeeks(1)
    }
}