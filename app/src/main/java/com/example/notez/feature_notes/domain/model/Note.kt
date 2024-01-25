package com.example.notez.feature_notes.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,

    val date: LocalDateTime,
    val title: String,
    val text: String
)

class InvalidNoteException(message: String): Exception(message)

