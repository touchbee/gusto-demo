package com.gusto.lunchmenu.domain.usecase

import java.time.LocalDate

class IsThisWeekUseCase(
    private val getWeekUseCase: GetWeekUseCase
) {

    /**
     * @return Returns true if we are in the given week
     */
    operator fun invoke(currentWeek: Int): Boolean {
        return currentWeek == getWeekUseCase(LocalDate.now())
    }
}