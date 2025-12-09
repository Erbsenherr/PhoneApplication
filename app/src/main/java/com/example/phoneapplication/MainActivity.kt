package com.example.phoneapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.phoneapplication.ui.SmartRoutineScreen
import com.example.phoneapplication.ui.theme.PhoneApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) { // onCreate ensures setup ONCE. It must be overwritten to add my code
        super.onCreate(savedInstanceState)               // Here we use the old code in the override and append the new code.
        setContent { // everything inside these brackets is the compose tree (previously: setContentView)
            PhoneApplicationTheme { //everything is wrapped herein, for consistent material design
                SmartRoutineScreen()
            }
        }
    }
}