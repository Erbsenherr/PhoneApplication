package com.example.phoneapplication.ui

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
import com.example.phoneapplication.viewModels.RoutineViewModel
import com.example.phoneapplication.taskClasses.SmartRoutineTask

// must not be class, only function. Otherwise, Compose cant work with it
    //class SmartRoutineScreen {

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmartRoutineScreen(
    viewModel: RoutineViewModel
) {
    val routines by viewModel.allTasks.collectAsState(initial = emptyList())


    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Routine Manager Demo") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.insertTask(
                    SmartRoutineTask(name = "Add Task", description = "Add Description")
                )
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add Task")
            }
        }
    ) { padding ->
        LazyColumn(contentPadding = padding) {
            items(routines) { task: SmartRoutineTask ->
                Text(
                    text = task.name, // a property of the task that is to be displayed
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
                Divider()
            }
        }
    }
}