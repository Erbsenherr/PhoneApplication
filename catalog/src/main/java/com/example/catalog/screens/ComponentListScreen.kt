package com.example.catalog.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ComponentListScreen(
    onHomeClick: () -> Unit, // declares variables (parameters) that hold a reference to a function
    onProfileClick: () -> Unit,
    onAdditionClick: () -> Unit
) {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("UI Catalog", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))
        ListItem(
            headlineContent = { Text("Home screen") },
            modifier = Modifier.fillMaxWidth().clickable { onHomeClick() }
        )
        Divider()
        ListItem(
            headlineContent = { Text("Profile screen") },
            modifier = Modifier.fillMaxWidth().clickable { onProfileClick() }
        )
        Divider()
        ListItem(
            headlineContent = { Text("Addition screen") },
            modifier = Modifier.fillMaxWidth().clickable { onAdditionClick() } // Has to be initialized in the beginning of the file!!!
        )
    }
}