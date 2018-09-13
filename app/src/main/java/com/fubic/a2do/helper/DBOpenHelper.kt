package com.fubic.a2do.helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

/**
 * Created by shoheohtani on 2018/09/13.
 */

class  DBOpenHelper(context: Context) : ManagedSQLiteOpenHelper(context, "2do.db", null, 1) {

    companion object {
        val tableName = "todo"
        private var instance : DBOpenHelper? = null

        fun getInstance(context: Context) : DBOpenHelper {
            return instance ?: DBOpenHelper(context.applicationContext)
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.run {
            createTable(tableName, ifNotExists = true, columns = *arrayOf("id" to INTEGER + PRIMARY_KEY, "title" to TEXT, "place" to TEXT, "date" to TEXT))
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

}