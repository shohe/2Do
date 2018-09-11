package com.fubic.a2do.view

import android.app.Dialog
import android.app.DialogFragment
import android.content.Context
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AlertDialog
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.Toast
import com.fubic.a2do.R
import com.fubic.a2do.activity.MainActivity
import java.text.AttributedCharacterIterator

/**
 * Created by shoheohtani on 2018/09/11.
 */

class NewTodoDialog : FrameLayout{

    constructor(context: Context) : super(context)

    private val dialogTodoTextView : EditText by lazy { findViewById(R.id.todoTextView) as EditText }
    private val dialogPlaceTextView : EditText by lazy { findViewById(R.id.placeTextView) as EditText }
    private val pickDateButton : Button by lazy { findViewById(R.id.pickDateButton) as Button }

    fun show() {
        val dialong = AlertDialog.Builder(context).apply {
            setTitle("Add Todo")
            setView(LayoutInflater.from(context).inflate(R.layout.add_todo_popup_view, null))
            setPositiveButton("Add", DialogInterface.OnClickListener{ _, _ ->
                Log.d("NewTodoDialog", "setPositiveButton")
            })
            setNegativeButton("Cancel", null)
        }.create()
        dialong.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
        dialong.show()
    }

}