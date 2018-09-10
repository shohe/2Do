package com.fubic.a2do.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.RadioButton
import android.widget.TextView
import com.fubic.a2do.R
import com.fubic.a2do.item.TodoListItem

/**
 * Created by shoheohtani on 2018/09/10.
 */

class TodoListView : FrameLayout {

    constructor(context: Context) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    var selectButton: RadioButton? = null
    var titleLabel: TextView? = null
    var placeLabel: TextView? = null
    var dateLabel: TextView? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.todo_list_view, this)
        this.selectButton = findViewById(R.id.selectButton) as RadioButton
        this.titleLabel = findViewById(R.id.titleLabel) as TextView
        this.placeLabel = findViewById(R.id.placeLabel) as TextView
        this.dateLabel = findViewById(R.id.dateLabel) as TextView
    }

    fun setTodo(item: TodoListItem) {
        this.titleLabel?.text = item.title
        this.placeLabel?.text = item.place
        this.dateLabel?.text = item.date.toString()
    }
}