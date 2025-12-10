package com.example.feature.home

import androidx.compose.runtime.Composable

data class HomeUiState(
    val greeting: String,
    val isLoading: Boolean,
    val items: List<String>
)

@Composable
fun HomeScreen(
    state: HomeUiState,
    onRefresh: () -> Unit,
    onItemClick: (String) -> Unit
) {
    // ... render your UI using only state and callbacks (no ViewModel here)
}