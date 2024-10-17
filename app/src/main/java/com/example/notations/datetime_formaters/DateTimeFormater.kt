package com.example.notations.datetime_formaters

import java.util.Date

interface DateTimeFormater {
    fun combineDateAndTime(selectedDate: Long, selectedTimeMinutes: Int): Date
    fun formatDateToString(date: Date): String
    fun formatDateToString(dateLong: Long): String
    fun formatTimeToString(time: Date): String
    fun formatTimeToString(timeLong: Long): String
}