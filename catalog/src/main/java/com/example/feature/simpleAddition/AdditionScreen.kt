package com.example.feature.simpleAddition

import androidx.compose.foundation.clickable
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


@Composable
fun AdditionScreen(
    state: AdditionUiState,
    onInputAChange: (String) -> Unit,
    onInputBChange: (String) -> Unit,
    onCalculateClick: () -> Unit,
    modifier: Modifier = Modifier,
    onClickReturn: () -> Unit
) {
    Column(modifier.padding(16.dp)) {
        Text("Add two numbers", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(
            value = state.inputA,
            onValueChange = onInputAChange, // a parameter of OutlinedTextField, takes a string!
                                            // it calls onInputAChange(DETECTED-STRING), i.e. that what was changed
                                            // onInputAChange itself is a function inside the vm...
                                            // ... which changes the state of InputA to the passed variable (DETECTED-STRING)
            label = { Text("First number") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = state.inputB,
            onValueChange = onInputBChange,
            label = { Text("Second number") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))

        Button(onClick = onCalculateClick, enabled = state.inputA.isNotBlank() && state.inputB.isNotBlank()) {
            Text("Calculate")
        }
        Divider()
        ListItem(
            headlineContent = { Text("return to menu") },
            modifier = Modifier.fillMaxWidth().clickable { onClickReturn() } // Has to be initialized in the beginning of the file!!!
        )

        Spacer(Modifier.height(16.dp))

        when {
            state.isLoading -> Text("Calculating…")
            state.errorMessage != null -> Text(state.errorMessage, color = MaterialTheme.colorScheme.error)
            state.result.isNotBlank() -> Text("Result: ${state.result}", style = MaterialTheme.typography.titleMedium)
        }
    }
}