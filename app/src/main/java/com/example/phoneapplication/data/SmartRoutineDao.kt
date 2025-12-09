package com.example.phoneapplication.data

import androidx.room.*
import com.example.phoneapplication.taskClasses.SmartRoutineTask
import kotlinx.coroutines.flow.Flow

@Dao
interface SmartRoutineDao {

    @Query("SELECT * FROM SmartRoutineTask")
    // get all tasks
    fun getAll(): Flow<List<SmartRoutineTask>> // suspend fun: suspend allows functions to run in background
                                                 // and without UI interference --> more control in general
                                                 // also apparently multithreading, no ui freeze

    // add a task
    @Insert
    suspend fun insert(task: SmartRoutineTask)

    // update a task
    @Update
    suspend fun update(task: SmartRoutineTask)

    // delete a task
    @Delete
    suspend fun delete(task: SmartRoutineTask)
}
