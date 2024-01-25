package com.example.notez.feature_notes.domain.use_cases

import com.example.notez.feature_notes.domain.model.InvalidNoteException
import com.example.notez.feature_notes.domain.model.Note
import com.example.notez.feature_notes.domain.repository.NoteRepository

class AddNote(
    private val repository: NoteRepository
) {
    @Throws(InvalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if(note.title.isBlank() && note.text.isBlank()) {
            throw InvalidNoteException("Deleted empty note")
        }
        repository.insertNote(note)
    }
}