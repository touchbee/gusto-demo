package com.gusto.lunchmenu.domain.usecase

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class GetFormattedTitleDateUseCase {

    /**
     * @return Formatted date string for title
     */
    operator fun invoke(date: LocalDate): String {
        val result = date.with(DayOfWeek.MONDAY)
        val formatter = DateTimeFormatter.ofPattern("MMMM d")
        return result.format(formatter)
    }
}