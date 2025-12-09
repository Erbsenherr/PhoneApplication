package com.example.phoneapplication.repository

import com.example.phoneapplication.data.AppDatabaseProvider
import com.example.phoneapplication.data.SmartRoutineDao
import com.example.phoneapplication.taskClasses.SmartRoutineTask
import android.content.Context
import kotlinx.coroutines.flow.Flow

class SmartRoutineRepository(context: Context) {

    // Obtain the DAO through the AppDatabaseProvider
    private val smartRoutineDao: SmartRoutineDao = AppDatabaseProvider.getInstance(context).smartRoutineDao() //smartRoutineDao() is defined in AppDatabase.kt

    // Dao is instantiated, we now create the operations we delegate to the Dao:

    // fetch all tasks (Room supports returning Flow, LiveData or suspend)
    fun getAllTasks(): Flow<List<SmartRoutineTask>> = smartRoutineDao.getAll()

    // insert task
    suspend fun insertTask(task: SmartRoutineTask) {
        smartRoutineDao.insert(task)
    }

    // Update task
    suspend fun updateTask(task: SmartRoutineTask) {
        smartRoutineDao.update(task)
    }

    // Delete task
    suspend fun deleteTask(task: SmartRoutineTask) {
        smartRoutineDao.delete(task)
    }
}