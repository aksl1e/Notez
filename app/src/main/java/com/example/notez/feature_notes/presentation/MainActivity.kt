package com.example.notez.feature_notes.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.notez.feature_notes.presentation.addEditNote.AddEditNoteScreen
import com.example.notez.feature_notes.presentation.addEditNote.AddEditNoteViewModel
import com.example.notez.feature_notes.presentation.notes.NotesScreen
import com.example.notez.feature_notes.presentation.notes.NotesViewModel
import com.example.notez.feature_notes.presentation.util.Screen
import com.example.notez.ui.theme.NotezTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotezTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.Notes.route
                    ) {
                        composable(
                            route = Screen.Notes.route
                        ) {
                            val viewModel = hiltViewModel<NotesViewModel>()
                            val state = viewModel.state

                            NotesScreen(
                                navController = navController,
                                state = state,
                                onEvent = viewModel::onEvent
                            )
                        }
                        composable(
                            route = Screen.AddEditNote.route +
                                    "?noteId={noteId}",
                            arguments = listOf(
                                navArgument(
                                    name = "noteId"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ) {
                            val viewModel = hiltViewModel<AddEditNoteViewModel>()
                            val state = viewModel.state

                            AddEditNoteScreen(
                                navController = navController,
                                state = state,
                                onEvent = viewModel::onEvent
                            )
                        }
                    }
                }
            }
        }
    }
}
