package com.example.notez.feature_notes.presentation.addEditNote

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notez.feature_notes.domain.model.InvalidNoteException
import com.example.notez.feature_notes.domain.model.Note
import com.example.notez.feature_notes.domain.use_cases.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(AddEditNoteState())
    val state: State<AddEditNoteState> = _state

    private var currentNoteId: Int? = null

    init {
        savedStateHandle.get<Int>("noteId")?.let { noteId ->
            if(noteId != -1) {
                viewModelScope.launch {
                    noteUseCases.getNote(noteId)?.also { note ->
                        currentNoteId = note.id
                        setupScreenWith(note)
                    }
                }
            }
        }
    }

    private fun setupScreenWith(note: Note) {
        _state.value = state.value.copy(
            titleState = note.title,
            titleHintVisibility = false,
            contentTextState = note.text,
            contentTextHintVisibility = false,
            dateState = note.date
        )
    }

    fun onEvent(event: AddEditNoteEvent) {
        when(event) {
            is AddEditNoteEvent.EnteredTitle -> {
                _state.value = state.value.copy(
                    titleState = event.value
                )
            }
            is AddEditNoteEvent.ChangeTitleFocus -> {
                _state.value = state.value.copy(
                    titleHintVisibility = !event.focusState.hasFocus &&
                        state.value.titleState.isBlank()
                )
            }
            is AddEditNoteEvent.EnteredContentText -> {
                _state.value = state.value.copy(
                    contentTextState = event.value
                )
            }
            is AddEditNoteEvent.ChangeContentTextFocus -> {
                _state.value = state.value.copy(
                    contentTextHintVisibility = !event.focusState.hasFocus &&
                            state.value.contentTextState.isBlank()
                )
            }

            is AddEditNoteEvent.SaveNote -> {
                viewModelScope.launch {
                    try {
                        noteUseCases.addNote(
                            Note(
                                title = state.value.titleState,
                                text = state.value.contentTextState,
                                date = LocalDateTime.now(),
                                id = currentNoteId
                            )
                        )
                    } catch (e: InvalidNoteException) {
                        Log.e("Error occurred", "yep")
                    }
                }
            }
        }
    }

}

