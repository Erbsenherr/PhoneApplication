package com.example.phoneapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.phoneapplication.ui.SmartRoutineScreen
import com.example.phoneapplication.ui.theme.PhoneApplicationTheme
import com.example.phoneapplication.repository.SmartRoutineRepository
import com.example.phoneapplication.viewModels.factories.RoutineViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.phoneapplication.viewModels.RoutineViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) { // onCreate ensures setup ONCE. It must be overwritten to add my code
        super.onCreate(savedInstanceState)               // Here we use the old code in the override and append the new code.

        val repository = SmartRoutineRepository(applicationContext)
        val factory = RoutineViewModelFactory(repository)

        setContent { // everything inside these brackets is the compose tree (previously: setContentView)
            PhoneApplicationTheme { //everything is wrapped herein, for consistent material design
                val vm: RoutineViewModel = viewModel(factory=factory)
                SmartRoutineScreen(viewModel=vm)
            }
        }
    }
}