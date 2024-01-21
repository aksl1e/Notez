package com.example.notez.feature_notes.domain.use_cases

import com.example.notez.feature_notes.domain.model.Note
import com.example.notez.feature_notes.domain.repository.NoteRepository

class GetNote(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(id: Int): Note? {
        return repository.getNoteById(id)
    }
}