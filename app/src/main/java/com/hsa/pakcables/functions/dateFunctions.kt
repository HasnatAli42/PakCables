package com.hsa.pakcables.functions

import java.text.SimpleDateFormat
import java.util.*

fun getCurrentDate() : Date{
    return Date()
}

fun formatDate(date: Date, format: String): String {
    val formatter = SimpleDateFormat(format, Locale.getDefault())
    return formatter.format(date)
}

fun viewDateFormat(date: Date) : String{
    return formatDate(date, "dd-MM-yyyy")
}