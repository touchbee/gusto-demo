package com.gusto.lunchmenu.domain.usecase

import java.time.DayOfWeek
import java.time.LocalDate

class GetCurrentWeekDayUseCase {

    /**
     * @return [DayOfWeek] for today
     */
    operator fun invoke(): DayOfWeek {
        return LocalDate.now().dayOfWeek
    }
}