package com.gusto.lunchmenu.ui

import com.gusto.lunchmenu.data.model.LunchMenu
import java.time.DayOfWeek

data class LunchMenuUiState(
    val menu: LunchMenu,
    val currentWeek: Int,
    val weekDay: DayOfWeek
)

sealed class UiState {
    data object Loading : UiState()
    data class Success(val data: LunchMenuUiState) : UiState()
    data class Error(val error: Throwable) : UiState()
}