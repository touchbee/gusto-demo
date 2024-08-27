package com.gusto.lunchmenu.data

import com.gusto.lunchmenu.data.model.RemoteLunchMenuItem
import kotlinx.coroutines.delay

class LunchMenuDataSource {

    suspend fun getLunchMenu(): List<List<RemoteLunchMenuItem>> {
        delay(3_000)
        return listOf(
            listOf(
                RemoteLunchMenuItem(
                    mainDish = "Chicken and waffles"
                ),
                RemoteLunchMenuItem(
                    mainDish = "Tacos"
                ),
                RemoteLunchMenuItem(
                    mainDish = "Curry"
                ),
                RemoteLunchMenuItem(
                    mainDish = "Pizza"
                ),
                RemoteLunchMenuItem(
                    mainDish = "Sushi"
                )
            ),
            listOf(
                RemoteLunchMenuItem(
                    mainDish = "Breakfast for lunch"
                ),
                RemoteLunchMenuItem(
                    mainDish = "Hamburgers"
                ),
                RemoteLunchMenuItem(
                    mainDish = "Spaghetti"
                ),
                RemoteLunchMenuItem(
                    mainDish = "Salmon"
                ),
                RemoteLunchMenuItem(
                    mainDish = "Sandwiches"
                )
            )
        )
    }
}