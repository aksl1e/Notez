package com.example.notez.feature_notes.data.type_converters

import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.TimeZone

class LocalDateTimeConverter {

    @TypeConverter
    fun fromTimestamp(timestamp: Long): LocalDateTime {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), TimeZone.getDefault().toZoneId())
    }

    @TypeConverter
    fun toTimestamp(localDateTime: LocalDateTime): Long {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
    }
}