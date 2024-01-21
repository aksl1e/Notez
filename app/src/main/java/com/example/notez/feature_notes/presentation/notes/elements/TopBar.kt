package com.example.notez.feature_notes.presentation.notes.elements

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import com.example.notez.feature_notes.domain.util.NoteOrder
import com.example.notez.feature_notes.presentation.notes.NotesEvent
import com.example.notez.feature_notes.presentation.notes.NotesState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    state: State<NotesState>,
    onEvent: (NotesEvent) -> Unit,
    scrollBehavior: () -> TopAppBarScrollBehavior
) {
    AnimatedVisibility(
        visible = !state.value.topBarSearchShown,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        TopAppBar(
            scrollBehavior = scrollBehavior(),
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary
            ),
            title = {
                Text("Notez")
            },
            actions = {
                IconButton(onClick = { onEvent(NotesEvent.ClickTopBarSearch) }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = "Search Bar"
                    )
                }

                IconButton(onClick = { onEvent(NotesEvent.ClickTopBarMenu) }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = "Top Bar Menu"
                    )
                    DropdownMenu(
                        expanded = state.value.topBarMenuExpanded,
                        onDismissRequest = { onEvent(NotesEvent.ClickTopBarMenu) }
                    ) {
                        DropdownMenuItem(
                            onClick = { onEvent(NotesEvent.Order(NoteOrder.Title)) },
                            text = { Text("Sort by Title") }
                        )
                        DropdownMenuItem(
                            onClick = { onEvent(NotesEvent.Order(NoteOrder.Date)) },
                            text = { Text("Sort by Date") }
                        )
                    }
                }

            }
        )
    }

    AnimatedVisibility(
        visible = state.value.topBarSearchShown,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        SearchBar(
            query = state.value.searchText,
            onQueryChange = { onEvent(NotesEvent.EnteredSearchText(it))
                onEvent(NotesEvent.SearchInDatabase(it))},
            onSearch = { onEvent(NotesEvent.SearchInDatabase(it)) },
            active = false,
            onActiveChange = {},
            placeholder = { Text("Search...") },
            shape = SearchBarDefaults.fullScreenShape,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = "Search Icon"
                )
            },
            trailingIcon = {
                IconButton(onClick = { onEvent(NotesEvent.ClickTopBarSearch) }) {
                    Icon(
                        imageVector = Icons.Default.ExitToApp,
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = "Hide Search"
                    )
                }
            }
        ) {

        }
    }
}




