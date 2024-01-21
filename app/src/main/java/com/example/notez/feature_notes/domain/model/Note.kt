package com.example.notez.feature_notes.domain.model

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,

    val date: LocalDateTime,
    val title: String,
    val content: Content
)

data class Content(
    val text: String,
    val images: List<Uri>
)

class InvalidNoteException(message: String): Exception(message)

