package com.example.feature.simpleAddition.viewModel

import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.addNumbers
import com.example.feature.simpleAddition.AdditionUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class AdditionViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel(){

    private val _uiState = MutableStateFlow(AdditionUiState()) // ? Does this create a AdditionUiState instance?
    val uiState: StateFlow<AdditionUiState> = _uiState

    fun onInputAChange(text: String) {
        _uiState.value = _uiState.value.copy(inputA = text, errorMessage = null ) // is the value in viewmodel or in AdditionUiState changed?
        savedStateHandle["inputA"] = text // which of the "inputA"-text is used to reference to AdditionStateUI?
    }

    fun onInputBChange(text: String) {
        _uiState.value = _uiState.value.copy(inputB = text, errorMessage = null) // ""
        savedStateHandle["inputB"] = text

    }
    fun onCalculateClick() { // why no type definition required?
        val a =
            _uiState.value.inputA.toDouble() // access internal variable or AdditionStateUI variable?
        val b = _uiState.value.inputB.toDouble()

        if (a == null || b == null) { // what is ||
            _uiState.value = _uiState.value.copy(errorMessage = "Please enter a valid number")
        }
        _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)


        val sum = addNumbers(a, b)

        _uiState.value = _uiState.value.copy(
            isLoading = false,
            result = sum.toString()
        )

        savedStateHandle["result"] = sum.toString()
    }
    init {
        val a = savedStateHandle.get<String>("inputA") ?: ""
        val b = savedStateHandle.get<String>("inputB") ?: ""
        val r = savedStateHandle.get<String>("result") ?: ""
        _uiState.value = _uiState.value.copy(inputA = a, inputB = b, result = r)
    }
}