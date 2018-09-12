package com.fubic.a2do.view

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import com.fubic.a2do.activity.MainActivity
import com.fubic.a2do.extension.toDate
import java.util.*

/**
 * Created by shoheohtani on 2018/09/11.
 */


interface DatePickDelegate {
    fun didSetDate(date: Date)
}

class DatePick : DialogFragment(), DatePickerDialog.OnDateSetListener {

    var delegate: DatePickDelegate? = null



    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(activity, this, year, month, day)
    }


    override fun onDateSet(view: DatePicker?, year: Int, month: Int, day: Int) {
        val dateStr = String.format(Locale.JAPAN, "%d/%d/%d", year, month + 1, day)
        val date: Date = dateStr.toDate() as Date
        this.delegate?.didSetDate(date)
    }

}