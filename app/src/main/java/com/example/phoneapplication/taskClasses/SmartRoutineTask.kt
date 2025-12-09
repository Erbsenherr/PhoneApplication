package com.example.phoneapplication.taskClasses

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SmartRoutineTask")
data class SmartRoutineTask(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name : String,
    val description: String,
    val startTime : Long? = null,
    val timeElapsed: Long = 0,
    val isCompleted: Boolean = false) {
}