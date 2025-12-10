package com.example.feature.profile

import androidx.compose.runtime.Composable

data class ProfileUiState(
    val name: String,
    val bio: String,
    val isEditing: Boolean
)

@Composable
fun ProfileScreen(
    state: ProfileUiState,
    onEditToggle: () -> Unit,
    onBioChange: (String) -> Unit
) {
    // ... stateless rendering based on state/callbacks
}