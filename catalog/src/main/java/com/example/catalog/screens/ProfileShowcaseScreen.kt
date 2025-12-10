package com.example.catalog.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.feature.profile.ProfileScreen
import com.example.feature.profile.ProfileUiState
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background


@Composable
fun ProfileShowcaseScreen() {
    var name by remember { mutableStateOf("Jane Doe") }
    var bio by remember { mutableStateOf("Compose enjoyer, Kotlin fanatic.") }
    var isEditing by remember { mutableStateOf(false) }

    val state = ProfileUiState(
        name = name,
        bio = bio,
        isEditing = isEditing)

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { isEditing = !isEditing }) {
                Text(if (isEditing) "Stop Editing" else "Edit")
            }
        }
        Spacer(Modifier.height(12.dp))
        if (isEditing) {
            OutlinedTextField(value = bio, onValueChange = { bio = it }, label = { Text("Bio") })
            Spacer(Modifier.height(8.dp))
        }

        Box(
            modifier = Modifier
                .weight(1f,fill = true)
                .fillMaxWidth()
                .background(Color(0xFFE0F7FA)) // light teal debug background

        ) {
        ProfileScreen(
            state = state,
            onEditToggle = { isEditing = !isEditing },
            onBioChange = { bio = it }
        ) }
    }
}