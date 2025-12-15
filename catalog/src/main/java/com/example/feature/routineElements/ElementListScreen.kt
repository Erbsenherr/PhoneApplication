package com.example.feature.routineElements

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DragHandle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import kotlin.math.absoluteValue


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ElementListScreen(
    state: RoutineElementListUiState,
    onLongPressToggleEditing: () -> Unit,
    onAddClick: () -> Unit,
    onRemoveClick: (Long) -> Unit,
    onItemClickWhileEditing: (Long) -> Unit,
    onMove: (fromIndex: Int, toIndex: Int) -> Unit,
    // Edit dialog controls
    onEditDialogTextChange: (String) -> Unit,
    onEditConfirm: () -> Unit,
    onEditCancel: () -> Unit
) {
    // Reorderable list state from the library
    val reorderState = rememberReorderableLazyListState(
        onMove = { from, to -> onMove(from.index, to.index) }
    )

    // Layout root
    Box(modifier = Modifier.fillMaxSize()) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                // Attach reorderable behavior to the list
                .reorderable(reorderState),
            state = reorderState.listState,
            contentPadding = PaddingValues(bottom = 88.dp) // space for FAB when editing
        ) {
            itemsIndexed(
                items = state.items,
                key = { _, item -> item.id }
            ) { index, item ->
                // Each item wrapped with ReorderableItem to animate during drag
                ReorderableItem(reorderState, key = item.id) { isDragging ->
                    val elevation = if (isDragging) 6.dp else 0.dp

                    // Row UI
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            // Long-press behavior:
                            // - If NOT editing: long-press anywhere toggles editing ON.
                            // - If editing: long-press on the non-handle area toggles editing OFF.
                            .then(
                                if (!state.isEditing) {
                                    Modifier.combinedClickable(
                                        onClick = {}, // do nothing when not editing
                                        onLongClick = onLongPressToggleEditing
                                    )
                                } else {
                                    Modifier.combinedClickable(
                                        onClick = { onItemClickWhileEditing(item.id) },
                                        onLongClick = onLongPressToggleEditing
                                    )
                                }
                            ),
                        elevation = CardDefaults.cardElevation(defaultElevation = elevation)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 12.dp, vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Drag handle only visible while editing.
                            if (state.isEditing) {
                                Icon(
                                    imageVector = Icons.Default.DragHandle,
                                    contentDescription = "Drag to reorder",
                                    modifier = Modifier
                                        .size(32.dp)
                                        // Long-press-and-drag on the handle starts reordering.
                                        .detectReorderAfterLongPress(reorderState)
                                        .semantics { contentDescription = "Reorder handle for ${item.title}" }
                                )
                                Spacer(Modifier.width(8.dp))
                            } else {
                                Spacer(Modifier.width(8.dp))
                            }

                            // Main content area (title)
                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(vertical = 8.dp)
                            ) {
                                Text(
                                    text = item.title,
                                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                                )
                                if (state.isEditing) {
                                    Text(
                                        text = "Tap to edit",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }

                            // Remove button appears only when editing
                            if (state.isEditing) {
                                IconButton(
                                    onClick = { onRemoveClick(item.id) },
                                    modifier = Modifier.semantics {
                                        contentDescription = "Remove ${item.title}"
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.error
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // Add button appears at the bottom when editing
        if (state.isEditing) {
            FloatingActionButton(
                onClick = onAddClick,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
                    .semantics { contentDescription = "Add element" }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }

        // Simple edit dialog while editing an item title
        if (state.editDialog != null) {
            EditElementDialog(
                value = state.editDialog.currentText,
                onValueChange = onEditDialogTextChange,
                onDismiss = onEditCancel,
                onConfirm = onEditConfirm
            )
        }
    }
}

@Composable
private fun EditElementDialog(
    value: String,
    onValueChange: (String) -> Unit,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Edit element") },
        text = {
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                singleLine = true,
                label = { Text("Title") }
            )
        },
        confirmButton = {
            TextButton(onClick = onConfirm) { Text("Save") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        }
    )
}

/* ================================
   Previews (easy to tweak the UI)
   ================================ */

@Preview(showBackground = true, name = "List - Not editing")
@Composable
private fun Preview_NotEditing() {
    MaterialTheme {
        ElementListScreen(
            state = RoutineElementListUiState(
                isEditing = false,
                items = listOf(
                    RoutineElementUiState(1, "Element #1"),
                    RoutineElementUiState(2, "Element #2"),
                    RoutineElementUiState(3, "Element #3")
                )
            ),
            onLongPressToggleEditing = {},
            onAddClick = {},
            onRemoveClick = {},
            onItemClickWhileEditing = {},
            onMove = { _, _ -> },
            onEditDialogTextChange = {},
            onEditConfirm = {},
            onEditCancel = {}
        )
    }
}

@Preview(showBackground = true, name = "List - Editing mode")
@Composable
private fun Preview_Editing() {
    MaterialTheme {
        ElementListScreen(
            state = RoutineElementListUiState(
                isEditing = true,
                items = listOf(
                    RoutineElementUiState(1, "Element #1"),
                    RoutineElementUiState(2, "Element #2"),
                    RoutineElementUiState(3, "Element #3")
                ),
                editDialog = null
            ),
            onLongPressToggleEditing = {},
            onAddClick = {},
            onRemoveClick = {},
            onItemClickWhileEditing = {},
            onMove = { _, _ -> },
            onEditDialogTextChange = {},
            onEditConfirm = {},
            onEditCancel = {}
        )
    }
}

@Preview(showBackground = true, name = "Edit Dialog")
@Composable
private fun Preview_EditDialog() {
    MaterialTheme {
        EditElementDialog(
            value = "Element #1",
            onValueChange = {},
            onDismiss = {},
            onConfirm = {}
        )
    }
}