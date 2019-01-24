package com.edgedevstudio.todolistapp_3.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.edgedevstudio.todolistapp_3.Database.AppDatabase

/**
 * Created by Olorunleke Opeyemi on 24/01/2019.
 **/
class AddTaskViewModelFactory(val database: AppDatabase, val taskId : Int)
    : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return super.create(modelClass)
    }
}