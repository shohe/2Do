package com.fubic.a2do.activity

import android.app.DatePickerDialog
import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.*
import com.fubic.a2do.R
import com.fubic.a2do.adapter.TodoListAdapter
import com.fubic.a2do.extension.toDate
import com.fubic.a2do.item.TodoListItem
import com.fubic.a2do.view.DatePick
import com.fubic.a2do.view.NewTodoDialog
import java.util.*

class MainActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    // XMLOutlet
    private val todoListView: ListView by lazy { findViewById(R.id.todoListView) as ListView }
    private val addButton: FloatingActionButton by lazy { findViewById(R.id.addButton) as FloatingActionButton }

    private val inflater: View by lazy { this.layoutInflater.inflate(R.layout.add_todo_popup_view, null, false) as View }
    private val dialogTodoTextView : EditText by lazy { inflater.findViewById(R.id.todoTextView) as EditText }
    private val dialogPlaceTextView : EditText by lazy { inflater.findViewById(R.id.placeTextView) as EditText }
    private val pickDateButton : Button by lazy { inflater.findViewById(R.id.pickDateButton) as Button }

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
            NewTodoDialog(this).show()
        }
    }


    fun showDatePickerDialog(v: View) {
        val fragment = DatePick()
        fragment.show(fragmentManager, "datePicker")
    }


    override fun onDateSet(view: DatePicker?, year: Int, month: Int, day: Int) {
        val string = String.format(Locale.JAPAN, "%d/%d/%d", year, month + 1, day)
        pickDateButton.text = string
    }

}
