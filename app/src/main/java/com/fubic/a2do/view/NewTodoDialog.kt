package com.fubic.a2do.view

import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.util.AttributeSet
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

    val dialogTodoTextView : EditText by lazy { findViewById(R.id.todoTextView) as EditText }

    init {

    }

    fun show() {
        dialogTodoTextView.requestFocus()

        val dialong = AlertDialog.Builder(context).apply {
            setTitle("Add Todo")
            //setView()
            setPositiveButton("Add", DialogInterface.OnClickListener{ _, _ ->
                Toast.makeText(context, dialogTodoTextView.text, Toast.LENGTH_LONG).show()
            })
            setNegativeButton("Cancel", null)
        }.create()
        dialong.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
        dialong.show()
    }

}