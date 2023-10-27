package com.fahad.todoapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fahad.todoapp.data.local.dto.LocalNote
import com.fahad.todoapp.domain.DISPLAY_TYPE
import com.fahad.todoapp.domain.MainViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen (
    viewModel: MainViewModel= hiltViewModel()

) {
    val stateUi = viewModel.stateUi

    var menuExpand by remember {
        mutableStateOf(false)
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Notes")
                },
                actions = {
                    IconButton(onClick = {
                        menuExpand = !menuExpand

                    }) {
                        Icon(imageVector = Icons.Filled.Menu, contentDescription = null)
                    }
                    DropdownMenu(
                        expanded = menuExpand,
                        onDismissRequest = {
                            menuExpand = false
                        }
                    )
                    {
                        DropdownMenuItem(
                            text = {
                                Text(text = "All")
                            },
                            onClick = {
                                viewModel.onDisplayTypeChange(DISPLAY_TYPE.ALL)
                                menuExpand = false

                            }
                        )
                        DropdownMenuItem(
                            text = {
                                Text(text = "Completed")
                            },
                            onClick = {
                                viewModel.onDisplayTypeChange(DISPLAY_TYPE.COMPLETED)
                                menuExpand = false

                            }
                        )
                        DropdownMenuItem(
                            text = {
                                Text(text = "Uncompleted")
                            },
                            onClick = {
                                viewModel.onDisplayTypeChange(DISPLAY_TYPE.UNCOMPLETED)
                                menuExpand = false

                            }
                        )
                        DropdownMenuItem(
                            text = {
                                Text(text = "Finished")
                            },
                            onClick = {
                                viewModel.onDisplayTypeChange(DISPLAY_TYPE.FINISHED)
                                menuExpand = false

                            }
                        )
                        DropdownMenuItem(
                            text = {
                                Text(text = "Unfinished")
                            },
                            onClick = {
                                viewModel.onDisplayTypeChange(DISPLAY_TYPE.UNFINISHED)
                                menuExpand = false

                            }
                        )

                    }


                }
            )
        },
        floatingActionButton = {
FloatingActionButton(
    onClick = {


    }
) {

                               Icon(
                                      imageVector = Icons.Filled.Add,
                                      contentDescription = null,
                                      modifier = Modifier.padding(16.dp)
                                 )


        }
        }


        ) { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues).fillMaxWidth()
        )
        {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(stateUi.value.currentNote) { note ->
                    NoteItem(
                        note = note,
                        onDoneClick = {
                            viewModel.update(
                                LocalNote(
                                    note.id,
                                    note.title,
                                    note.content,
                                    note.timestamp,
                                    note.color,
                                    note.isSynced,
                                    note.description,
                                    note.isPinned,
                                    note.isArchived,
                                    note.isFinished,
                                )
                            )
                        },
                        onDeleteClick = {
                            viewModel.delete(note)

                        }
                    )
                }
            }
        }
    }
}






















