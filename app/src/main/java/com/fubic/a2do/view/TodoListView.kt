package com.fubic.a2do.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.*
import com.fubic.a2do.R
import com.fubic.a2do.item.TodoListItem

/**
 * Created by shoheohtani on 2018/09/10.
 */

interface TodoListViewDelegate {
    fun didSelectItem(listView: TodoListView, id: Int, isChecked: Boolean)
}

class TodoListView : FrameLayout {

    // XMLOutlet
    val checkBox: CheckBox by lazy { findViewById(R.id.checkBox) as CheckBox }
    val titleLabel: TextView by lazy { findViewById(R.id.titleLabel) as TextView }
    val placeLabel: TextView by lazy { findViewById(R.id.placeLabel) as TextView }
    val dateLabel: TextView by lazy { findViewById(R.id.dateLabel) as TextView }
    // -
    private var id: Int? = 0

    var delegate: TodoListViewDelegate? = null


    constructor(context: Context) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    init {
        LayoutInflater.from(context).inflate(R.layout.todo_list_view, this)
        this.addCheckedHanlder()
    }


    fun setTodo(item: TodoListItem) {
        this.id = item.id
        this.titleLabel.text = item.title
        this.placeLabel.text = item.place
        this.dateLabel.text = item.date.toString()
    }


    private fun addCheckedHanlder() {
        this.checkBox.setOnClickListener {
            delegate?.didSelectItem(this, this.id!!, this.checkBox.isChecked)
        }
    }
}