package com.edgedevstudio.todolistapp_3.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.edgedevstudio.todolistapp_3.Database.TaskEntry

/**
 * Created by Olorunleke Opeyemi on 21/01/2019.
 **/

@Dao
interface TaskDAO {

    @Query("SELECT * FROM task ORDER BY priority")
    fun loadAllTask() : LiveData<MutableList<TaskEntry>>

    @Insert
    fun insertTask(taskEntry : TaskEntry)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateTask(taskEntry : TaskEntry)

    @Delete
    fun deleteTask(taskEntry : TaskEntry)

    @Query("SELECT * FROM task WHERE id = :id")
    fun loadTaskById(id : Int) : LiveData<TaskEntry>
}