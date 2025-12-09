package com.example.phoneapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import com.example.phoneapplication.taskClasses.SmartRoutineTask
import com.example.phoneapplication.ui.theme.PhoneApplicationTheme
import com.example.phoneapplication.taskManagers.RoutineManager
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import com.example.phoneapplication.data.SmartRoutineDao
import com.example.phoneapplication.data.AppDatabase
import androidx.lifecycle.viewModelScope

class MainActivity : ComponentActivity() {

    private val routineManager = RoutineManager()

    // private val routineList = mutableListOf<SmartRoutineTask>()   | depricated, I use 'rooms' now

    override fun onCreate(savedInstanceState: Bundle?) { // I need to do this, because I want to add code to onCreate.
        super.onCreate(savedInstanceState)               // Because onCreate is inherited through its parent, I need to
                                                         // change the original onCreate. By callin onCreate(SavedInstanceState) first,
                                                         // in the changed version, the original code is executed first, than the code added afterwards

        setContentView(R.layout.smartroutine_layout)     // sets the layout to smartroutine_layout

        // get UI references:

        val input = findViewById<EditText>(R.id.taskDescriptionInput)
        val button = findViewById<Button>(R.id.addTaskButton)

        // initialize db-connection
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "smart-routine-db"
        ).build()

        // initialize db DAO:
        val smartRoutineDao = db.smartRoutineDao() // smartRoutineDao is created INSIDE the db.script (AppDatabase)

        // set Button click listener:

        button.setOnClickListener {
            val description = input.text.toString().trim()
            if (description.isNotEmpty()) {
                val newRoutineTask = SmartRoutineTask(description=description)

                // add task manager logic here!


                    // routineList.add(newRoutineObject)    | depricated, I use rooms now
                // Save task using DAO inside a coroutine!



                // !!!!!!!!!!!!!!!!!! Must be moved to viewModel !!!!!!!!!!!!!!!!!!!!!!!!!

                GlobalScope.launch { // launches a coroutine, a lightweight thread of asynchronous code
                                     // everything inside these brackets runs as a coroutine
                                     // .launch returns immediately and does not block the thread its called from
                    smartRoutineDao.insert(newRoutineTask)

                } // !!!!!!!!!!!!!!!!!! Must be moved to viewModel !!!!!!!!!!!!!!!!!!!!!!!!!



                // TODO: update UI (like RecyclerView) here

                input.text.clear() // Text is cleared for convenciance

            }
        }
        enableEdgeToEdge()
        setContent {
            PhoneApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PhoneApplicationTheme {
        Greeting("Android")
    }
}