package com.example.notez.feature_notes.presentation.notes

import com.example.notez.feature_notes.domain.model.Note
import com.example.notez.feature_notes.domain.util.NoteOrder

data class NotesState (
    val notes: List<Note> = emptyList(),
    val noteOrder: NoteOrder = NoteOrder.Date,
    val searchText: String = "",
    val topBarMenuExpanded: Boolean = false,
    val topBarSearchShown: Boolean = false
)
