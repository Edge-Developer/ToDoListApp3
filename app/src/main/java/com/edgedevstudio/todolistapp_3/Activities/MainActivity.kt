package com.edgedevstudio.todolistapp_3.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edgedevstudio.todolistapp_3.Database.AppDatabase
import com.edgedevstudio.todolistapp_3.Async.AppExecutors
import com.edgedevstudio.todolistapp_3.R
import com.edgedevstudio.todolistapp_3.TaskAdapter
import com.edgedevstudio.todolistapp_3.ViewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), TaskAdapter.TaskViewCliskListener {
    private lateinit var mTaskAdapter: TaskAdapter
    private lateinit var appDatabase: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerViewTasks.layoutManager = LinearLayoutManager(this)

        mTaskAdapter = TaskAdapter(this, this)
        recyclerViewTasks.adapter = mTaskAdapter

        // just to add line dividers to each task
        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recyclerViewTasks.addItemDecoration(decoration)

        //recognises when a user swipes (a taskEntry) to delete
        ItemTouchHelper(
            object : ItemTouchHelper.SimpleCallback
                (0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                // Called when a user swipes left or right on a ViewHolder
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {

                    val position = viewHolder.adapterPosition
                    val tasks = mTaskAdapter.mTaskEntries
                    val task = tasks[position]
                    AppExecutors.getInstance().diskIO.execute { appDatabase.taskDao().deleteTask(task) }
                }
            }
        ).attachToRecyclerView(recyclerViewTasks)



        fab.setOnClickListener {
            val intent = Intent(this, AddTaskActivity::class.java)
            startActivity(intent)
        }

        appDatabase = AppDatabase.getInstance(this)
        setupViewModel()
    }

    private fun setupViewModel(){
        Log.d(TAG, "Retrieve Tasks from DB Method")

        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.tasksLiveData.observe(this, Observer {
            taskEntries ->
            mTaskAdapter.setTaskEntries(taskEntries)
            Log.d(TAG, "Change Occured in our DB")
        })
    }

    override fun onTaskViewClickListener(taskId: Int) {
        val intent = Intent(this, AddTaskActivity::class.java)
        intent.putExtra(AddTaskActivity.EXTRA_TASK_ID, taskId)
        startActivity(intent)
    }

   val TAG = "MainActivity"
}
