package com.fubic.a2do.helper

import com.fubic.a2do.extension.toDate
import com.fubic.a2do.item.TodoListItem
import org.jetbrains.anko.db.MapRowParser
import java.util.*

/**
 * Created by shoheohtani on 2018/09/13.
 */

class ListDataParser : MapRowParser<TodoListItem> {

    override fun parseRow(columns: Map<String, Any?>): TodoListItem {
        return TodoListItem(columns["id"] as Int, columns["title"] as String, columns["place"] as String, (columns["date"] as String).toDate("yyyy/MM/dd") as Date)
    }

}