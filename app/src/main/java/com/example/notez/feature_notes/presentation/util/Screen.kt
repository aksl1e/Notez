package com.example.notez.feature_notes.presentation.util

sealed class Screen(val route: String) {
    data object Notes: Screen("notes")
    data object AddEditNote: Screen("addEditNote")
}