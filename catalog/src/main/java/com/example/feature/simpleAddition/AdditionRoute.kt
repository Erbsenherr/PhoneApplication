package com.example.feature.simpleAddition

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.feature.simpleAddition.viewModel.AdditionViewModel



@Composable
fun AdditionRoute(
    navController: NavController,
    viewModel: AdditionViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    AdditionScreen( // I assume the container calls AdditionScreen with the state derived from hiltViewModel?
        state = state,
        onInputAChange = viewModel::onInputAChange,
        onInputBChange = viewModel::onInputBChange,
        onCalculateClick = viewModel::onCalculateClick,
        modifier = Modifier.fillMaxSize(),
        onClickReturn = {
            navController.navigate("list") {
                popUpTo(navController.graph.startDestinationId) { inclusive = false }
                launchSingleTop = true
            }
        }
    )
}