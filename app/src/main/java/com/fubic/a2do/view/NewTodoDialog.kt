package com.fubic.a2do.view

import android.app.FragmentManager
import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import com.fubic.a2do.R
import java.util.*

/**
 * Created by shoheohtani on 2018/09/11.
 */

interface NewTodoDialogDelegate {
    fun didPressAddTodo(todo: String, place: String, date: Date)
}


class NewTodoDialog : FrameLayout, DatePickDelegate {

    // XMLOutlet
    private val inflater: View by lazy { LayoutInflater.from(context).inflate(R.layout.add_todo_popup_view, this) }
    private val dialogTodoTextView : EditText by lazy { findViewById(R.id.todoTextView) as EditText }
    private val dialogPlaceTextView : EditText by lazy { findViewById(R.id.placeTextView) as EditText }
    private val pickDateButton : Button by lazy { inflater.findViewById(R.id.pickDateButton) as Button }
    // -
    private var selectDate: Date = Date()

    var delegate: NewTodoDialogDelegate? = null
    var fragmentManager: FragmentManager? = null



    constructor(context: Context) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    init {
        this.addPickDateButtonEvent()
    }


    fun show() {
        dialogTodoTextView.requestFocus()

        val dialong = AlertDialog.Builder(context).apply {
            setTitle("Add Todo")
            setView(inflater)
            setPositiveButton("Add", DialogInterface.OnClickListener{ _, _ ->
                val date = Date()
                delegate?.didPressAddTodo(dialogTodoTextView.text.toString(), dialogPlaceTextView.text.toString(), selectDate)
            })
            setNegativeButton("Cancel", null)
        }.create()
        dialong.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
        dialong.show()
    }


    private fun addPickDateButtonEvent() {
        pickDateButton.setOnClickListener {
            if (this.fragmentManager != null) {
                val datePicker: DatePick = DatePick()
                datePicker.delegate = this
                datePicker.show(fragmentManager!!, "datePicker")
            } else {
                Log.e("NewTodoDialog", "ERROR: fragmentManager is null")
            }
        }
    }


    override fun didSetDate(date: Date) {
        this.pickDateButton.text = date.toString()
        this.selectDate = date
    }

}