package com.fubic.a2do.view

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.Toast
import com.fubic.a2do.R

/**
 * Created by shoheohtani on 2018/09/11.
 */

class NewTodoDialog : FrameLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    val todoTextView : EditText by lazy { findViewById(R.id.todoTextView) as EditText }

    init {
        LayoutInflater.from(context).inflate(R.layout.add_todo_popup_view, this)
    }

    fun create() : Dialog {
        Log.d("NewTodoDialog", "show()")

        todoTextView.requestFocus()
        val dialog = AlertDialog.Builder(context).apply {
            setTitle("Add Todo")
//            setView(this)
            setPositiveButton("Add", DialogInterface.OnClickListener{ _, _ ->
                Toast.makeText(context, todoTextView.text, Toast.LENGTH_LONG).show()
            })
            setNegativeButton("Cancel", null)
        }.create()
        dialog.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
        return dialog
    }

//    fun initDialog() {
//        val inflater = this.layoutInflater.inflate(R.layout.add_todo_popup_view, null, false)
//        val dialogTodoTextView : EditText by lazy {
//            inflater.findViewById(R.id.todoTextView) as EditText
//        }
//        dialogTodoTextView.requestFocus()
//
//        val dialong = AlertDialog.Builder(this).apply {
//            setTitle("Add Todo")
//            setView(inflater)
//            setPositiveButton("Add", DialogInterface.OnClickListener{ _, _ ->
//                Toast.makeText(context, dialogTodoTextView.text, Toast.LENGTH_LONG).show()
//            })
//            setNegativeButton("Cancel", null)
//        }.create()
//        dialong.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
//        dialong.show()
//    }

}