package com.gusto.lunchmenu.domain.usecase

import java.time.LocalDate

class GetPreviousWeekUseCase {

    /**
     * @return A [Pair] of week and year or null
     */
    operator fun invoke(date: LocalDate): LocalDate {
        return date.minusWeeks(1)
    }
}