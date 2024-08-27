package com.gusto.lunchmenu.domain.usecase

import java.time.LocalDate
import java.time.temporal.WeekFields
import java.util.Locale

class GetWeekUseCase {

    /**
     * @return Week in year for given date
     */
    operator fun invoke(date: LocalDate): Int {
        return date.get(
            WeekFields.of(Locale.US).weekOfYear()
        )
    }
}