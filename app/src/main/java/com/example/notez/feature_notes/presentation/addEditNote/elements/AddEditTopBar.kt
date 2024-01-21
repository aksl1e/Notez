package com.example.notez.feature_notes.presentation.addEditNote.elements

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import com.example.notez.feature_notes.presentation.addEditNote.AddEditNoteEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditTopBar(
    onEvent: (AddEditNoteEvent) -> Unit
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
        title = {
            Text("Add and Edit")
        },
        actions = {
            IconButton(onClick = { onEvent(AddEditNoteEvent.PickPhoto) }) {
                Icon(
                    imageVector = Icons.Default.Photo,
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = "Add Photo"
                )
            }
        }
    )
}