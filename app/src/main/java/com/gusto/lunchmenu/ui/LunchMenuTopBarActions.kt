package com.gusto.lunchmenu.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.gusto.lunchmenu.R

@Composable
fun LunchMenuTopBarActions(
    onClickPreviousWeek: () -> Unit,
    onClickNextWeek: () -> Unit
) {
    IconButton(
        onClick = {
            onClickPreviousWeek()
        }
    ) {
        Icon(
            Icons.AutoMirrored.Filled.KeyboardArrowLeft,
            contentDescription = stringResource(id = R.string.toolbar_previous_week)
        )
    }

    IconButton(
        onClick = {
            onClickNextWeek()
        }
    ) {
        Icon(
            Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = stringResource(id = R.string.toolbar_next_week)
        )
    }
}