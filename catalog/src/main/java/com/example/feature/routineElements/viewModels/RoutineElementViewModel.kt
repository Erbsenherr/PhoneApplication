package com.example.feature.routineElements.viewModels

import androidx.lifecycle.ViewModel
import com.example.feature.routineElements.EditDialogState
import com.example.feature.routineElements.RoutineElementListUiState
import com.example.feature.routineElements.RoutineElementUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class RoutineElementViewModel @Inject constructor(
    // later I can put repositories here!
) : ViewModel() // I define this class to be of type ViewModel
{
    // Declare internal mutable state:
    private val _state = MutableStateFlow(
        RoutineElementListUiState(
            isEditing = false,
            items = List(5) { // now 5, later we want Logic here!!!!!
                index -> RoutineElementUiState(id = index.toLong(), title = "Element #$index")
                    // INPUT -> OUTPUT (meaning: index is input -> a ElementUi state object is output
            }
        )
    )
    val state: StateFlow<RoutineElementListUiState> = _state.asStateFlow()

    fun toggleEditingMode() {
        _state.value = _state.value.copy(isEditing = !_state.value.isEditing)
    }

    // add a new element at the end (decoy only)
    fun addElement() {
        val current = _state.value
        val newId = (current.items.maxOfOrNull { it.id } ?: -1L) + 1L
        val updated = current.items + RoutineElementUiState(id = newId, title = "New Element")
        _state.value = current.copy(items = updated)
    }
    // Remove by id
    fun removeElement(id: Long) {
        val current = _state.value
        _state.value = current.copy(items = current.items.filterNot { it.id == id })
    }

    // Tap on an item while editing -> open edit dialog
    fun startEditingItem(id: Long) {
        val item = _state.value.items.firstOrNull { it.id == id } ?: return
        _state.value = _state.value.copy(editDialog = EditDialogState(
            itemId = id,
            currentText = item.title
        )
        )
    }

    fun updateEditDialogText(newText: String) {
        val dialog = _state.value.editDialog ?: return
        _state.value = _state.value.copy(editDialog = dialog.copy(currentText = newText))
    }

    fun confirmEdit() {
        val dialog = _state.value.editDialog ?: return
        val current = _state.value
        val updated = current.items.map {
            if (it.id == dialog.itemId) it.copy(title = dialog.currentText) else it
        }
        _state.value = current.copy(items = updated, editDialog = null)
    }

    fun cancelEdit() {
        _state.value = _state.value.copy(editDialog = null)
    }

    // Reorder list by swapping the item from one index to another index (drag-and-drop)
    fun moveItem(fromIndex: Int, toIndex: Int) {
        val current = _state.value
        if (fromIndex == toIndex) return
        val mutable = current.items.toMutableList()
        val item = mutable.removeAt(fromIndex)
        mutable.add(toIndex, item)
        _state.value = current.copy(items = mutable)
    }
}