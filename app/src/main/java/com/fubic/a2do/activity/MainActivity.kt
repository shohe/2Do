package com.fubic.a2do.activity

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.WindowManager
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import com.fubic.a2do.R
import com.fubic.a2do.adapter.TodoListAdapter
import com.fubic.a2do.item.TodoListItem
import com.fubic.a2do.view.NewTodoDialog
import java.util.*

class MainActivity : AppCompatActivity() {

    // XMLOutlet
    private val todoListView: ListView by lazy {
        findViewById(R.id.todoListView) as ListView
    }
    private val addButton: FloatingActionButton by lazy {
        findViewById(R.id.addButton) as FloatingActionButton
    }
    // -
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
            this.showDialog()

            // todo: separate dialog codes
            // NewTodoDialog(applicationContext).create().show()
        }
    }

    fun showDialog() {
        val inflater = this.layoutInflater.inflate(R.layout.add_todo_popup_view, null, false)
        val dialogTodoTextView : EditText by lazy {
            inflater.findViewById(R.id.todoTextView) as EditText
        }
        val dialogPlaceTextView : EditText by lazy {
            inflater.findViewById(R.id.placeTextView) as EditText
        }
        dialogTodoTextView.requestFocus()

        val dialong = AlertDialog.Builder(this).apply {
            setTitle("Add Todo")
            setView(inflater)
            setPositiveButton("Add", DialogInterface.OnClickListener{ _, _ ->
                val item = TodoListItem(0, dialogTodoTextView.text.toString(), dialogPlaceTextView.text.toString(), Date())
                itemAdapter!!.items.add(item)
                itemAdapter!!.notifyDataSetChanged()
            })
            setNegativeButton("Cancel", null)
        }.create()
        dialong.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
        dialong.show()
    }
}
