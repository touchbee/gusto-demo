package com.gusto.lunchmenu.data.model

data class LunchMenuItem(
    val entree: String,
    val mainDish: String,
    val dessert: String
)

fun LunchMenuItem.displayString(): String {
    return "$entree, $mainDish, $dessert"
}

data class LunchMenu(
    val itemMonday: LunchMenuItem,
    val itemTuesday: LunchMenuItem,
    val itemWednesday: LunchMenuItem,
    val itemThursday: LunchMenuItem,
    val itemFriday: LunchMenuItem
)

fun RemoteLunchMenuItem.toLunchMenuItem(): LunchMenuItem {
    return LunchMenuItem(
        entree,
        mainDish,
        dessert
    )
}