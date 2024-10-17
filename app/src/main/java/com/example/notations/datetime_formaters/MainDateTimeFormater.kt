package com.example.notations.datetime_formaters

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import java.util.Date
import java.util.Locale

class MainDateTimeFormater : DateTimeFormater {
    override fun combineDateAndTime(selectedDate: Long, selectedTimeMinutes: Int): Date {
        val calendar = Calendar.getInstance().apply {
            timeInMillis = selectedDate
        }
        val hours = selectedTimeMinutes / 60
        val minutes = selectedTimeMinutes % 60
        calendar.set(Calendar.HOUR_OF_DAY, hours)
        calendar.set(Calendar.MINUTE, minutes)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.time
    }

    override fun formatDateToString(date: Date): String {
        val formatDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return formatDate.format(date)
    }

    override fun formatDateToString(dateLong: Long): String {
        val date = Date(dateLong)
        val formatDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return formatDate.format(date)
    }

    override fun formatTimeToString(time: Date): String {
        val formatTime = SimpleDateFormat("HH:mm", Locale.getDefault())
        return formatTime.format(time)
    }

    override fun formatTimeToString(timeLong: Long): String {
        val time = Date(timeLong)
        val formatTime = SimpleDateFormat("HH:mm", Locale.getDefault())
        return formatTime.format(time)
    }
}