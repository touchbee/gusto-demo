package com.gusto.lunchmenu.domain.usecase

import java.time.LocalDate

class GetCurrentWeekUseCase(
    private val getWeekUseCase: GetWeekUseCase
) {

    /**
     * @return Week in year for today
     */
    operator fun invoke(): Int {
        return getWeekUseCase(
            LocalDate.now()
        )
    }
}

