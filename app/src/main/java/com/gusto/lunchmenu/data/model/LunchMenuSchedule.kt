package com.gusto.lunchmenu.data.model

/**
 * Data model holding the list of [LunchMenuWeek]s
 */
data class LunchMenuSchedule(
    val menuByWeek: List<LunchMenuWeek>
)

fun LunchMenuSchedule.menuForWeek(week: Int): LunchMenu? {
    return if (week % 2 == 1) {
        menuByWeek[0].menu
    } else {
        menuByWeek[1].menu
    }
}
