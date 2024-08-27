package com.gusto.lunchmenu.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.gusto.lunchmenu.R

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun LunchMenuScreen() {

    val viewModel: LunchMenuViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()
    val titleDate by viewModel.formattedTitleDate
    val isThisWeek by viewModel.isThisWeek

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if(isThisWeek) {
                            stringResource(id = R.string.toolbar_title_this_week)
                        } else {
                            stringResource(id = R.string.toolbar_title, titleDate)
                        }
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceTint,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                    actionIconContentColor = Color.White
                ),
                actions = {
                    LunchMenuTopBarActions(
                        onClickPreviousWeek = {
                            viewModel.previousWeek()
                        }, onClickNextWeek = {
                            viewModel.nextWeek()
                        }
                    )
                }
            )
        }
    ) { innerPadding ->
        LunchMenuContent(
            uiState,
            isThisWeek,
            Modifier.padding(innerPadding),
            onRetry = {
                viewModel.retry()
            }
        )
    }
}
