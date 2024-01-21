package com.example.notez.feature_notes.presentation.addEditNote

import android.net.Uri
import java.time.LocalDateTime

data class AddEditNoteState (
    val titleState: String = "",
    val titleHintVisibility: Boolean = true,

    val dateState: LocalDateTime = LocalDateTime.now(),

    val contentTextState: String = "",
    val contentTextHintVisibility: Boolean = true,

    val contentImagesState: List<Uri> = emptyList()
)