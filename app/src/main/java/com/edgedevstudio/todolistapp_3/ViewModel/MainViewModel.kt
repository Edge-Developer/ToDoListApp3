package com.edgedevstudio.todolistapp_3.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.edgedevstudio.todolistapp_3.Database.AppDatabase
import com.edgedevstudio.todolistapp_3.Database.TaskEntry

/**
 * Created by Olorunleke Opeyemi on 24/01/2019.
 **/
class MainViewModel(app : Application) : AndroidViewModel(app){

    val tasksLiveData : LiveData<MutableList<TaskEntry>>
    val TAG = "MainActivity"

    init {
        Log.d(TAG, "Initialized MainViewModel")
        val database = AppDatabase.getInstance(getApplication())
        tasksLiveData = database.taskDao().loadAllTask()
    }
}