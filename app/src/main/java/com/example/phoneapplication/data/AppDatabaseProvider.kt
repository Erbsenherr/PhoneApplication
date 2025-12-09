package com.example.phoneapplication.data

import android.content.Context
import androidx.room.Room

// function of this: Singleton manager, which ensures exactly ONE instance of the database exists at all times.

    // Kotlin objects always are singleton!
    // Kotlin objects load, when the code is accessed for the first time (e.g. when calling 'getInstance'). From then on
    //        it exists in memory
object AppDatabaseProvider {
    @Volatile // @Volatile ensures multi-thread visibility. All threads see newly changed values immediately.
              // This means all threads always work with the same and latest values.

    private var INSTANCE: AppDatabase? = null // initialization

    fun getInstance(context: Context): AppDatabase {  // capital C Context = Android context class
        return INSTANCE ?: synchronized(this) { //synchronized: thread safety. Only one thread can access this at the same time

            // Room.databaseBuilder is expensive, we only want to do it once. Hence the AppDatabaseProvider...
            val instance = Room.databaseBuilder( // databaseBuilder: checks if db exists, otherwise: creates new
                                                 // context: makes app ressources availabe, enables file access, "smart path manager"
                context.applicationContext,      // applicationContext: context lives as long as app is in processo
                AppDatabase::class.java,
                "smart-routine-db" // name of the db file
            ).build()
            INSTANCE = instance
            instance
        }
    }
}