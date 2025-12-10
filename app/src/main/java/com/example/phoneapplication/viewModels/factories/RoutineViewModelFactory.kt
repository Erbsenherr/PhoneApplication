package com.example.phoneapplication.viewModels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.example.phoneapplication.repository.SmartRoutineRepository
import com.example.phoneapplication.viewModels.RoutineViewModel


class RoutineViewModelFactory(
    private val repository: SmartRoutineRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RoutineViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RoutineViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}