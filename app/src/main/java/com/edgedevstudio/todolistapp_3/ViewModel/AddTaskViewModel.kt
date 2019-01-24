package com.edgedevstudio.todolistapp_3.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.edgedevstudio.todolistapp_3.Database.AppDatabase
import com.edgedevstudio.todolistapp_3.Database.TaskEntry

/**
 * Created by Olorunleke Opeyemi on 24/01/2019.
 **/
class AddTaskViewModel(database: AppDatabase, taskId: Int ) : ViewModel() {
    val task : LiveData<TaskEntry>

    init {
        task = database.taskDao().loadTaskById(taskId)
    }
}