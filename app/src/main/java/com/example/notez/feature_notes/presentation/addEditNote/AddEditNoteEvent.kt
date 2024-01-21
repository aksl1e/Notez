package com.example.notez.feature_notes.presentation.addEditNote

import androidx.compose.ui.focus.FocusState

sealed class AddEditNoteEvent {
    data class EnteredTitle(val value: String): AddEditNoteEvent()
    data class ChangeTitleFocus(val focusState: FocusState): AddEditNoteEvent()

    data class EnteredContentText(val value: String): AddEditNoteEvent()
    data class ChangeContentTextFocus(val focusState: FocusState): AddEditNoteEvent()

    data object PickPhoto: AddEditNoteEvent()

    data object SaveNote: AddEditNoteEvent()

}