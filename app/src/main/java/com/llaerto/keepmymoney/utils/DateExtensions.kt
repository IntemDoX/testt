package com.llaerto.keepmymoney.utils

import java.text.DateFormat
import java.util.*

private val calendar = Calendar.getInstance()

fun Calendar.getDate(year: Int, month: Int, dayOfMonth: Int): Date {
    set(year, month, dayOfMonth)
    return time
}

fun Date.isSameDay(date: Date): Boolean {
    val date1 = Calendar.getInstance()
    val date2 = Calendar.getInstance()
    date1.time = this
    date2.time = date
    return date1.get(Calendar.YEAR) == date2.get(Calendar.YEAR) && date1.get(Calendar.MONTH) == date2.get(Calendar.MONTH) &&
            date1.get(Calendar.DAY_OF_MONTH) == date2.get(Calendar.DAY_OF_MONTH)
}

fun Date.getDateYear(): Int {
    calendar.time = this
    return calendar.get(Calendar.YEAR)
}

fun Date.getDateMonth(): Int {
    calendar.time = this
    return calendar.get(Calendar.MONTH)
}

fun Date.getDateDay(): Int {
    calendar.time = this
    return calendar.get(Calendar.DAY_OF_MONTH)
}

fun Date.addDay(): Date {
    calendar.time = this
    calendar.add(Calendar.DATE, 1)
    return calendar.time
}

fun Date.getNormalizedTime(): Long {
    return GregorianCalendar().run {
        with(Calendar.getInstance().apply { time = this@getNormalizedTime }) {
            this@run.timeZone = TimeZone.getTimeZone("UTC")
            this@run.set(Calendar.YEAR, get(Calendar.YEAR))
            this@run.set(Calendar.MONTH, get(Calendar.MONTH))
            this@run.set(Calendar.DAY_OF_MONTH, get(Calendar.DAY_OF_MONTH))
            this@run.set(Calendar.HOUR_OF_DAY, 0)
            this@run.set(Calendar.MINUTE, 0)
            this@run.set(Calendar.SECOND, 0)
            this@run.set(Calendar.MILLISECOND, 0)
        }
        time.time
    }
}

fun Date.inPeriod(from: Date, to: Date): Boolean {
    return (after(from) || isSameDay(from)) && (before(to) || isSameDay(to))
}

fun Long.toShortDate(): String {
    return DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(this)
}
