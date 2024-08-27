package com.gusto.lunchmenu.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.gusto.lunchmenu.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupComposeUI()
    }

    private fun setupComposeUI() {
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                LunchMenuScreen()
            }
        }
    }
}

