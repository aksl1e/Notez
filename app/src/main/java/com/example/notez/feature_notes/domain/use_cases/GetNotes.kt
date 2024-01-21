package com.example.notez.feature_notes.domain.use_cases

import com.example.notez.feature_notes.domain.model.Note
import com.example.notez.feature_notes.domain.repository.NoteRepository
import com.example.notez.feature_notes.domain.util.NoteOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotes(
    private val repository: NoteRepository
) {
    operator fun invoke(
        noteOrder: NoteOrder = NoteOrder.Title,
        keyWord: String = ""
    ): Flow<List<Note>> {
        return repository.getNotes().map { notes ->
            when(noteOrder) {
                is NoteOrder.Title -> {
                    if(keyWord != "") {
                        notes.filter { it.title.contains(keyWord) || it.content.text.contains(keyWord) }
                    } else
                        notes.sortedBy { it.title.lowercase() }
                }
                is NoteOrder.Date -> {
                    if(keyWord != "") {
                        notes.filter { it.title.contains(keyWord) || it.content.text.contains(keyWord) }
                    } else
                        notes.sortedByDescending { it.date }
                }
            }
        }
    }
}