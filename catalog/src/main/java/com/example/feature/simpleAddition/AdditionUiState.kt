package com.example.feature.simpleAddition

// this is the UiState. It is where all the data that is displayed or put in the UI is "stored".
    // it is stored, but really it is just read from the viewModel
// it interacts directly with the ui and the ViewScreen
// "Its the photo of the current screen content"
data class AdditionUiState(
    val inputA: String = "",
    val inputB: String = "",
    val result: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null
    )
{}