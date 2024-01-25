package com.example.notez.feature_notes.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.notez.feature_notes.data.type_converters.LocalDateTimeConverter
import com.example.notez.feature_notes.domain.model.Note

@Database(
    entities = [Note::class],
    version = 1
)
@TypeConverters(
    LocalDateTimeConverter::class
)
abstract class NoteDatabase : RoomDatabase() {
    abstract val noteDao: NoteDao

    companion object{
        const val DATABASE_NAME = "notes_db"
    }
}