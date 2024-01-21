package com.example.notez.feature_notes.domain.util

sealed class NoteOrder {
    data object Date : NoteOrder()
    data object Title : NoteOrder()
}