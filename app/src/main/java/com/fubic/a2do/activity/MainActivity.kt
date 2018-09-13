package com.fubic.a2do.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.util.Log
import android.widget.*
import com.fubic.a2do.R
import com.fubic.a2do.adapter.TodoListAdapter
import com.fubic.a2do.helper.DBOpenHelper
import com.fubic.a2do.helper.ListDataParser
import com.fubic.a2do.item.TodoListItem
import com.fubic.a2do.view.NewTodoDialog
import com.fubic.a2do.view.NewTodoDialogDelegate
import com.fubic.a2do.view.TodoListView
import com.fubic.a2do.view.TodoListViewDelegate
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity(), NewTodoDialogDelegate, TodoListViewDelegate {

    // XMLOutlet
    private val todoListView: ListView by lazy { findViewById(R.id.todoListView) as ListView }
    private val addButton: FloatingActionButton by lazy { findViewById(R.id.addButton) as FloatingActionButton }
    private val removeButton: FloatingActionButton by lazy { findViewById(R.id.removeButton) as FloatingActionButton }

    // -
    private var itemAdapter: TodoListAdapter? = null
    private var selectedItems: ArrayList<Int> = ArrayList()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.initAddButton()
        this.initRemoveButton()
        this.initListView()
        this.addTapHandler(this.todoListView)
        this.addLongTapHandler(this.todoListView)
    }


    private fun initListView() {
        val items = this.fetchDataList() as ArrayList<TodoListItem>
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


    private fun initRemoveButton() {
        this.animateRemoveButton(true)

        this.removeButton.setOnClickListener {
            for (id in this.selectedItems) {
                val item = this.todoListView.getItemAtPosition(id)
                this.itemAdapter!!.items.remove(item)
            }
            this.itemAdapter!!.notifyDataSetChanged()
            this.todoListView.adapter = this.itemAdapter
            this.selectedItems.clear()
            this.updateButtonStatus()
        }
    }


    private fun fetchDataList() : List<TodoListItem> {
        val helper : DBOpenHelper = DBOpenHelper.getInstance(this)
        val dataList = helper.readableDatabase.select(DBOpenHelper.tableName).parseList<TodoListItem>(ListDataParser())
        return dataList
    }


    private fun updateButtonStatus() {
        this.animateRemoveButton(true)
        this.animateAddButton(false)
    }


    private fun addTapHandler(listView: ListView) {
        listView.setOnItemClickListener { parent, view, position, id ->
            val item = listView.adapter.getItem(position) as TodoListItem
        }
    }


    private fun addLongTapHandler(listView: ListView) {
        listView.setOnItemLongClickListener { parent, view, position, id ->
            this.itemAdapter!!.items.remove(this.itemAdapter!!.getItem(position))
            this.itemAdapter!!.notifyDataSetChanged()
            return@setOnItemLongClickListener true
        }
    }


    private fun animateAddButton(isHidden: Boolean) {
        if (isHidden) {
            this.addButton.isClickable = false
            this.addButton.hide()
            this.addButton.animate().translationY(300.0f).alpha(1.0f).setListener(null)
        } else {
            this.addButton.isClickable = true
            this.addButton.show()
            this.addButton.animate().translationY(0.0f).alpha(1.0f).setListener(null)
        }
    }

    private fun animateRemoveButton(isHidden: Boolean) {
        if (isHidden) {
            this.removeButton.isClickable = false
            this.removeButton.hide()
            this.removeButton.animate().translationY(300.0f).alpha(1.0f).setListener(null)
        } else {
            this.removeButton.isClickable = true
            this.removeButton.show()
            this.removeButton.animate().translationY(0.0f).alpha(1.0f).setListener(null)
        }
    }


    // ---
    //  NewTodoDialogDelegate
    override fun didPressAddTodo(todo: String, place: String, date: Date) {
        val item = TodoListItem(this.itemAdapter!!.items.size, todo, place, date)
        this.itemAdapter!!.items.add(item)
        this.itemAdapter!!.notifyDataSetChanged()

        val helper: DBOpenHelper = DBOpenHelper.getInstance(this)
        helper.use {
            insert(DBOpenHelper.tableName, *arrayOf("id" to item.id, "title" to item.title, "place" to item.place, "date" to item.date.toString()))
        }
    }


    // ---
    //  TodoListViewDelegate
    override fun didSelectItem(listView: TodoListView, id: Int, isChecked: Boolean) {
        if (isChecked) {
            this.selectedItems.add(id)
            this.animateAddButton(true)
            this.animateRemoveButton(false)
        } else {
            this.selectedItems.remove(id)
            if (this.selectedItems.size == 0) {
                this.animateAddButton(false)
                this.animateRemoveButton(true)
            }
        }
    }

}
