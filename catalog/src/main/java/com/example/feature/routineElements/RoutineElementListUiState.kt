package com.example.feature.routineElements

// Everything the screen needs to draw right now.
// In plain terms: a single "bag of data" the UI reads from.
data class RoutineElementListUiState(
    val isEditing: Boolean = false,
    val items: List<RoutineElementUiState> = emptyList(),
    val editDialog: EditDialogState? = null // Non-null when editing a single item
)
// State for the "edit item" dialog
data class EditDialogState(
    val itemId: Long,
    val currentText: String
)