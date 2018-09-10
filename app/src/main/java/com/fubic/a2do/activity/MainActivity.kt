package com.fubic.a2do.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.fubic.a2do.R
import com.fubic.a2do.adapter.TodoListAdapter
import com.fubic.a2do.item.TodoListItem
import com.fubic.a2do.view.TodoListView
import java.util.*

class MainActivity : AppCompatActivity() {

    var todoListView: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.initListView()
//        val todoListView = TodoListView(applicationContext)
//        val item = TodoListItem(0, "studying kotlin", "office", Date())
//        todoListView.setTodo(item)
//        setContentView(todoListView)
    }

    fun initListView() {
        this.todoListView = findViewById(R.id.todoListView) as ListView

        val items = arrayListOf<TodoListItem>()
        for (i in 0..5) {
            val item = TodoListItem(i, "studying kotlin", "office", Date())
            items.add(item)
        }
        var adapter = TodoListAdapter(applicationContext, items)
        this.todoListView!!.adapter = adapter
    }
}
