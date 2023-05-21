package com.hsa.pakcables.functions

import androidx.room.TypeConverter
import java.util.*

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

}

class DoubleListTypeConverters {
    @TypeConverter
    fun fromListDoubleToString(intList: List<Double>): String = intList.toString()
    @TypeConverter
    fun toListDoubleFromString(stringList: String): List<Double> {
        val result = ArrayList<Double>()
        val split =stringList.replace("[","").replace("]","").replace(" ","").split(",")
        for (n in split) {
            try {
                result.add(n.toDouble())
            } catch (e: Exception) {

            }
        }
        return result
    }
}
