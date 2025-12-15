package com.example.phoneapplication.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "SmartRoutineData")
data class SmartRoutineTask(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    //@ForeignKey() // I NEED TO ADD A FOREIGN KEY -> Primary Key of SmartRoutineTask
    val date : String,
    val name : String,
    val isCompleted: Boolean = false,
    val timeElapsed: Long = 0) {
}