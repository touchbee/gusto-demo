package com.gusto.lunchmenu.data.model

/**
 * Data model maps week/year pair to the [LunchMenu]
 */
data class LunchMenuWeek(
    val week: Int,
    val year: Int,
    val menu: LunchMenu
)