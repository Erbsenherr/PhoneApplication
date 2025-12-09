package com.example.phoneapplication.viewModels


// imports for viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.Flow

// repository imports

import com.example.phoneapplication.repository.SmartRoutineRepository
import com.example.phoneapplication.taskClasses.SmartRoutineTask

class RoutineViewModel(
    private val repository: SmartRoutineRepository // our repository object/class
) : ViewModel() {

    // expose the list of tasks as Flow
    val allTasks: Flow<List<SmartRoutineTask>> = repository.getAllTasks() // the function to query the database

    fun insertTask(task: SmartRoutineTask) {
        viewModelScope.launch {
            repository.insertTask(task)
        }
    }

    // All the other operations based upon UI-interaction go here!

}