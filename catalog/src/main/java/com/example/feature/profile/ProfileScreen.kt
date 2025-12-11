package com.example.feature.profile

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel // deprecated because of factory?
import androidx.compose.material.icons.filled.Add


data class ProfileUiState(
    val name: String,
    val bio: String,
    val isEditing: Boolean
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    state: ProfileUiState,             // Immutable UI state to render
    onEditToggle: () -> Unit,          // Toggle editing mode (Edit / Done)
    onBioChange: (String) -> Unit,     // User typed new bio text
    modifier: Modifier = Modifier      // Allows parent to control sizing (fillMaxSize, padding, etc.)
) {
    // Use a Scaffold so the screen has a top bar and a content area that is properly bounded.
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profile") }, // Simple title; you could show state.name here
                actions = {
                    // Action toggles edit mode; label reflects the current mode.
                    TextButton(onClick = onEditToggle) {
                        Text(if (state.isEditing) "Done" else "Edit")
                    }
                }
            )
        }
    ) { innerPadding ->
        // Screen content area
        Column(
            modifier = modifier
                .fillMaxSize()                  // Occupy all available space inside the Scaffold content area
                .padding(innerPadding)          // Respect Scaffold insets
                .padding(16.dp)                 // Page padding
        ) {
            // Always show the name prominently
            Text(
                text = state.name,
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(Modifier.height(8.dp))

            if (state.isEditing) {
                // EDIT MODE: show editable bio field
                OutlinedTextField(
                    value = state.bio,                 // Current bio
                    onValueChange = onBioChange,       // Emit changes upstream (to parent state)
                    label = { Text("Bio") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(8.dp))

                Text(
                    text = "Editing mode is ON",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            } else {
                // VIEW MODE: show bio as text
                Text(
                    text = state.bio.ifBlank { "No bio yet." }, // Fallback if empty
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            // You can add more sections below: avatar, stats, lists, etc.
        }
    }
}