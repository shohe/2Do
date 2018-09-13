package com.fubic.a2do.helper

import android.util.Log
import com.fubic.a2do.extension.toDate
import com.fubic.a2do.item.TodoListItem
import org.jetbrains.anko.db.MapRowParser
import java.util.*

/**
 * Created by shoheohtani on 2018/09/13.
 */

class ListDataParser : MapRowParser<TodoListItem> {

    override fun parseRow(columns: Map<String, Any?>): TodoListItem {
        var date: Date = Date()
        if (columns["date"] == null) date = (columns["date"] as String).toDate() as Date
        return TodoListItem((columns["id"] as Long).toInt(), columns["title"] as String, columns["place"] as String, date)
    }

}