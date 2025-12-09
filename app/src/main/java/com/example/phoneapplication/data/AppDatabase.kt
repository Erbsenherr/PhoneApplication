package com.example.phoneapplication.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.phoneapplication.taskClasses.SmartRoutineTask


// Function of AppDatabase.kt: defines, what Entities and DAOs belong to the Database. Its the Databases' Blueprint

@Database( // declaration of RoomDatabase with Entities and a version
    entities = [SmartRoutineTask::class],   // more enteties can be added if required
    version = 1,                             // start at 1, number increments when schema changes
    exportSchema = false                    // optinal, useful for learning/projects
)

abstract class AppDatabase : RoomDatabase() {
    // returns an instance of the DAO
    abstract fun smartRoutineDao(): SmartRoutineDao // returns an Instance of the DAO.
                                                    // ususally multiple DAOs per Database,
                                                    // one per Concern (Single Responsibility principle)
}