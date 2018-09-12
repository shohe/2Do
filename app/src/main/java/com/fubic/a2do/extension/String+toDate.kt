package com.fubic.a2do.extension

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by shoheohtani on 2018/09/11.
 */

fun String.toDate(pattern: String = "yyyy/MM/dd"): Date? {
    val sdFormat = try {
        SimpleDateFormat(pattern)
    } catch (e: IllegalArgumentException) {
        null
    }
    val date = sdFormat?.let {
        try {
            it.parse(this)
        } catch (e: ParseException) {
            null
        }
    }
    return date
}