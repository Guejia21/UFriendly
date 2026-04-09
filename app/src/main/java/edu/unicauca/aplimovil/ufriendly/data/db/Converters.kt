package edu.unicauca.aplimovil.ufriendly.data.db

import androidx.room.TypeConverter
import java.util.Date

//The database is smart enough to determine when use those converters
class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}
