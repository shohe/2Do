package com.fubic.a2do.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.util.Log
import android.widget.ListView
import com.fubic.a2do.R
import com.fubic.a2do.adapter.TodoListAdapter
import com.fubic.a2do.item.TodoListItem
import java.util.*

class MainActivity : AppCompatActivity() {

    // XMLOutlet
    private val todoListView: ListView by lazy {
        findViewById(R.id.todoListView) as ListView
    }
    private val addButton: FloatingActionButton by lazy {
        findViewById(R.id.addButton) as FloatingActionButton
    }

    private var itemAdapter: TodoListAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.initListView()
        this.initAddButton()
    }

    fun initListView() {
        val items = arrayListOf<TodoListItem>()
        for (i in 0..5) {
            val item = TodoListItem(i, "studying kotlin", "office", Date())
            items.add(item)
        }
        this.itemAdapter = TodoListAdapter(applicationContext, items)
        this.todoListView.adapter = this.itemAdapter
    }

    fun initAddButton() {
        this.addButton.setOnClickListener {
            Log.d("MainActivity", "msg")
        }
    }


}
