package com.fubic.a2do.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.util.Log
import android.widget.*
import com.fubic.a2do.R
import com.fubic.a2do.adapter.TodoListAdapter
import com.fubic.a2do.item.TodoListItem
import com.fubic.a2do.view.NewTodoDialog
import com.fubic.a2do.view.NewTodoDialogDelegate
import com.fubic.a2do.view.TodoListView
import com.fubic.a2do.view.TodoListViewDelegate
import kotlinx.android.synthetic.main.activity_main.view.*
import java.util.*


class MainActivity : AppCompatActivity(), NewTodoDialogDelegate, TodoListViewDelegate {

    // XMLOutlet
    private val todoListView: ListView by lazy { findViewById(R.id.todoListView) as ListView }
    private val addButton: FloatingActionButton by lazy { findViewById(R.id.addButton) as FloatingActionButton }

    // -
    private var itemAdapter: TodoListAdapter? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.initAddButton()
        this.initListView()
        this.addTapHandler(this.todoListView)
        this.addLongTapHandler(this.todoListView)
    }


    private fun initListView() {
        val items = arrayListOf<TodoListItem>()
        for (i in 0..5) {
            val item = TodoListItem(i, "studying kotlin", "office", Date())
            items.add(item)
        }
        this.itemAdapter = TodoListAdapter(this, items)
        this.itemAdapter!!._delegate = this
        this.todoListView.adapter = this.itemAdapter
    }


    private fun initAddButton() {
        this.addButton.setOnClickListener {
            val ntd = NewTodoDialog(this)
            ntd.delegate = this
            ntd.fragmentManager = fragmentManager
            ntd.show()
        }
    }


    private fun addTapHandler(listView: ListView) {
        listView.setOnItemClickListener { parent, view, position, id ->
            val item = listView.adapter.getItem(position) as TodoListItem
            Log.d("item id:", "${item.id}")
        }
    }


    private fun addLongTapHandler(listView: ListView) {
        listView.setOnItemLongClickListener { parent, view, position, id ->
            this.itemAdapter!!.items.remove(this.itemAdapter!!.getItem(position))
            this.itemAdapter!!.notifyDataSetChanged()
            return@setOnItemLongClickListener true
        }
    }


    // ---
    //  NewTodoDialogDelegate
    override fun didPressAddTodo(todo: String, place: String, date: Date) {
        val item = TodoListItem(this.itemAdapter!!.items.size, todo, place, date)
        this.itemAdapter!!.items.add(item)
        this.itemAdapter!!.notifyDataSetChanged()
    }


    // ---
    //  TodoListViewDelegate
    override fun didSelectItem(id: Int, isChecked: Boolean) {
        Log.d("MainActivity", "id: $id, isChecked: $isChecked")
    }

}
