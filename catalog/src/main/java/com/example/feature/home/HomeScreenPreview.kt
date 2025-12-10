package com.example.feature.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.catalog.screens.HomeShowcaseScreen
import com.example.designsystem.AppTheme

@Preview
@Composable
fun HomeScreen_Preview() {
    AppTheme {
        HomeScreen (
            state = HomeUiState("Hello", isLoading = false, items = listOf("One","Two")),
            onRefresh = {},
            onItemClick = {}
        )
    }
}