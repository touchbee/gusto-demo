package com.gusto.lunchmenu.domain.usecase

import com.gusto.lunchmenu.data.model.LunchMenu
import com.gusto.lunchmenu.data.model.LunchMenuSchedule
import com.gusto.lunchmenu.data.model.menuForWeek

class GetMenuForWeekUseCase {

    /**
     * @return [LunchMenu] or null for given week and [LunchMenuSchedule]
     */
    operator fun invoke(week: Int, schedule: LunchMenuSchedule): LunchMenu? {
        return schedule.menuForWeek(week)
    }
}