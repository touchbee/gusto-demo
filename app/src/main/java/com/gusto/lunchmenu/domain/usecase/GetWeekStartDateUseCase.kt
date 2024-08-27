package com.gusto.lunchmenu.domain.usecase

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.IsoFields
import java.time.temporal.TemporalAdjusters

class GetWeekStartDateUseCase {

    /**
     * @return The date for the beginning of the week for given arguments
     */
    operator fun invoke(date: LocalDate): LocalDate {
        return date
            .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
    }
}