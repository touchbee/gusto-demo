package com.gusto.lunchmenu.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LunchMenuContent(
    uiState: UiState,
    isThisWeek: Boolean,
    modifier: Modifier = Modifier,
    onRetry: () -> Unit
) {
    when (uiState) {
        is UiState.Loading -> {
            LoadingScreen(
                modifier = modifier
            )
        }
        is UiState.Error -> {
            ErrorScreen(
                modifier = modifier
            ) {
                onRetry()
            }
        }
        is UiState.Success -> {
            LunchMenuWeekList(
                isThisWeek = isThisWeek,
                weekDay = uiState.data.weekDay,
                lunchMenu = uiState.data.menu,
                modifier = modifier
            )
        }
    }
}