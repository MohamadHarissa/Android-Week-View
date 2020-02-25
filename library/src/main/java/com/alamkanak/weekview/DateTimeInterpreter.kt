package com.alamkanak.weekview

import android.content.Context
import java.text.DateFormat
import java.util.*

/**
 * Created by Raquib on 1/6/2015.
 */
interface DateTimeInterpreter {
    fun interpretDate(date: Calendar): String
    fun interpretTime(hour: Int): String
    fun setDateFormat(dateFormat: DateFormat)
}

class DefaultDateTimeInterpreter(
        context: Context,
        numberOfDays: Int
) : DateTimeInterpreter {

    private var sdfDate = DateFormat.getDateInstance();
    private val sdfTime = DateUtils.getDefaultTimeFormat(context)
    private val calendar = Calendar.getInstance()

    fun setNumberOfDays(numberOfDays: Int) {
        sdfDate = DateUtils.getDefaultDateFormat(numberOfDays)
    }

    override fun interpretDate(date: Calendar): String {
        return sdfDate.format(date.time).toUpperCase()
    }

    override fun interpretTime(hour: Int): String {
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, 0)
        return sdfTime.format(calendar.time)
    }

    override fun setDateFormat(dateFormat : DateFormat){
        sdfDate = dateFormat
    }
}
