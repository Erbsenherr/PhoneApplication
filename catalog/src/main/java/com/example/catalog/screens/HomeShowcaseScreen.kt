package com.example.catalog.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.feature.home.HomeScreen
import com.example.feature.home.HomeUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HomeShowcaseScreen() {
    var isLoading by remember { mutableStateOf(false) }
    var items by remember { mutableStateOf(listOf("Alpha", "Beta", "Gamma")) }
    val scope = rememberCoroutineScope()

    val state = HomeUiState(
        greeting = "Hello, Compose!",
        isLoading = isLoading,
        items = items
    )

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = {
                isLoading = true
                scope.launch {
                    delay(1200)
                    items = items.shuffled()
                    isLoading = false
                }
            }) { Text("Simulate refresh") }

            Button(onClick = { items = emptyList() }) { Text("Empty list") }
        }
        Spacer(Modifier.height(12.dp))
        HomeScreen(
            state = state,
            onRefresh = { /* handled above */ },
            onItemClick = { /* no-op in catalog */ }
        )
    }
}