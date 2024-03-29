package com.example.notez.feature_notes.domain.use_cases

import com.example.notez.feature_notes.domain.model.Note
import com.example.notez.feature_notes.domain.repository.NoteRepository

class DeleteNote(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(note: Note) {
        repository.deleteNote(note)
    }
}