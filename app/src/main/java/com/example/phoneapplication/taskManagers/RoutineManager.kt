package com.example.phoneapplication.taskManagers

import com.example.phoneapplication.taskClasses.SmartRoutineTask

class RoutineManager {

    fun initializeSmartRoutineObject(task: SmartRoutineTask): SmartRoutineTask {
        return task.copy(startTime = System.currentTimeMillis())
    }
}