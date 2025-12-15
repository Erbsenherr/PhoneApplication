package com.example.feature.routineElements

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController

import com.example.feature.routineElements.viewModels.RoutineElementViewModel

// Container: gets data from the ViewModel and passes it into the stateless Screen.
// This makes the UI easy to Preview and test, and keeps ViewModel/Hilt out of Previews.
@Composable
fun ElementListRoute(
    viewModel: RoutineElementViewModel = hiltViewModel(),
    navController: NavController,
) {
    val state by viewModel.state.collectAsState()

    ElementListScreen(
        state = state,
        onLongPressToggleEditing = { viewModel.toggleEditingMode() },
        onAddClick = { viewModel.addElement() },
        onRemoveClick = { id -> viewModel.removeElement(id) },
        onItemClickWhileEditing = { id -> viewModel.startEditingItem(id) },
        onMove = { from, to -> viewModel.moveItem(from, to) },
        onEditDialogTextChange = { viewModel.updateEditDialogText(it) },
        onEditConfirm = { viewModel.confirmEdit() },
        onEditCancel = { viewModel.cancelEdit() }
    )
}
