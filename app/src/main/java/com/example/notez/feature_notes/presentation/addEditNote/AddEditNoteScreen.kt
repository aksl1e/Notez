package com.example.notez.feature_notes.presentation.addEditNote

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.notez.feature_notes.presentation.addEditNote.elements.AddEditTopBar
import com.example.notez.feature_notes.presentation.addEditNote.elements.MyTextField
import java.time.format.DateTimeFormatter

@Composable
fun AddEditNoteScreen(
    navController: NavController,
    state: State<AddEditNoteState>,
    onEvent: (AddEditNoteEvent) -> Unit
) {
    Scaffold(
        topBar = { AddEditTopBar() },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onEvent(AddEditNoteEvent.SaveNote)
                    navController.navigateUp()
                },
                containerColor = MaterialTheme.colorScheme.primary,
                shape = CircleShape,
            ) {
                Icon(
                    imageVector = Icons.Default.Save,
                    contentDescription = "Save Task"
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = paddingValues.calculateTopPadding(),
                    start = 15.dp,
                    end = 15.dp,
                    bottom = 0.dp
                )
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = state.value.dateState
                    .format(DateTimeFormatter.ofPattern("dd.MM, HH:mm"))
                    .toString(),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(26.dp))

            MyTextField(
                text = state.value.titleState,
                onValueChange = {
                    onEvent(AddEditNoteEvent.EnteredTitle(it))
                },
                onFocusChange = {
                    onEvent(AddEditNoteEvent.ChangeTitleFocus(it))
                },
                isHintVisible = state.value.titleHintVisibility,
                textStyle = MaterialTheme.typography.headlineMedium.copy(color = Color.White),
                hint = "Title"
            )

            Spacer(modifier = Modifier.height(16.dp))

            MyTextField(
                text = state.value.contentTextState,
                onValueChange = {
                    onEvent(AddEditNoteEvent.EnteredContentText(it))
                },
                onFocusChange = {
                    onEvent(AddEditNoteEvent.ChangeContentTextFocus(it))
                },
                textStyle = MaterialTheme.typography.bodyMedium.copy(color = Color.White),
                hint = "Type your thoughts..",
                isHintVisible = state.value.contentTextHintVisibility
            )
        }
    }
}