package com.example.notez.feature_notes.presentation.notes

import com.example.notez.feature_notes.domain.model.Note
import com.example.notez.feature_notes.domain.util.NoteOrder

sealed class NotesEvent {
    data class Order(val noteOrder: NoteOrder) : NotesEvent()
    data class DeleteNote(val note: Note) : NotesEvent()
    data object ClickTopBarMenu: NotesEvent()
    data object ClickTopBarSearch: NotesEvent()
    data class EnteredSearchText(val value: String): NotesEvent()
    data class SearchInDatabase(val value: String): NotesEvent()
}