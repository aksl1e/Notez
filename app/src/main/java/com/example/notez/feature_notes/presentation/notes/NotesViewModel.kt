package com.example.notez.feature_notes.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notez.feature_notes.domain.use_cases.NoteUseCases
import com.example.notez.feature_notes.domain.util.NoteOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel()  {
    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state

    private var getNotesJob: Job? = null

    init {
        getNotes(NoteOrder.Date, "")
    }

    fun onEvent(event: NotesEvent) {
        when(event) {
            is NotesEvent.ClickTopBarMenu -> {
                _state.value = state.value.copy(
                    topBarMenuExpanded = !state.value.topBarMenuExpanded
                )
            }
            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    noteUseCases.deleteNote(event.note)
                }
            }

            is NotesEvent.Order -> {
                if(state.value.noteOrder::class == event.noteOrder::class)
                    return
                getNotes(event.noteOrder, "")
            }

            is NotesEvent.ClickTopBarSearch -> {
                _state.value = state.value.copy(
                    topBarSearchShown = !state.value.topBarSearchShown
                )
                if(!state.value.topBarSearchShown)
                    _state.value = state.value.copy(
                        searchText = ""
                    )
            }

            is NotesEvent.EnteredSearchText -> {
                _state.value = state.value.copy(
                    searchText = event.value
                )
            }

            is NotesEvent.SearchInDatabase -> {
                getNotes(state.value.noteOrder, event.value)
            }
        }
    }

    private fun getNotes(noteOrder: NoteOrder, keyWord: String){
        getNotesJob?.cancel()
        getNotesJob = noteUseCases.getNotes(noteOrder, keyWord)
            .onEach { notes ->
                _state.value = state.value.copy(
                    notes = notes,
                    noteOrder = noteOrder
                )
            }
            .launchIn(viewModelScope)
    }
}