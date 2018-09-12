package com.fubic.a2do.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.fubic.a2do.item.TodoListItem
import com.fubic.a2do.view.TodoListView
import com.fubic.a2do.view.TodoListViewDelegate

/**
 * Created by shoheohtani on 2018/09/10.
 */

class TodoListAdapter(private val context: Context, var items: ArrayList<TodoListItem>) : BaseAdapter() {

    var _delegate: TodoListViewDelegate? = null



    init {

    }

    override fun getCount(): Int {
        return this.items.count()
    }

    override fun getItem(position: Int): Any {
        return this.items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = (convertView as? TodoListView) ?: TodoListView(context).apply {
            setTodo(items[position])
            delegate = _delegate
        }
        return view
    }
}