package com.fahad.todoapp.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fahad.todoapp.R
import com.fahad.todoapp.domain.DISPLAY_TYPE
import com.fahad.todoapp.domain.MainViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen(
    viewModel: MainViewModel = hiltViewModel()
) {
    val stateUi = viewModel.stateUi

    var menuExpand by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Notes") },
                actions = {
                    IconButton(onClick = { menuExpand = !menuExpand }) {
                        Icon(
                            painter = painterResource(id = R.drawable.outline_filter_list_24),
                            contentDescription = null)
                    }
                    DropdownMenu(
                        expanded = menuExpand,
                        onDismissRequest = { menuExpand = false }
                    ) {
                        val displayTypes = listOf(
                            "All" to DISPLAY_TYPE.ALL,

                            "Finished" to DISPLAY_TYPE.FINISHED,
                            "Unfinished" to DISPLAY_TYPE.UNFINISHED
                        )
                        displayTypes.forEach { (text, displayType) ->
                            DropdownMenuItem(
                                text = { Text(text) },
                                onClick = {
                                    viewModel.onDisplayTypeChange(displayType)
                                    menuExpand = false
                                }
                            )
                        }
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog = true },
                shape = RoundedCornerShape(50),
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = null, tint = Color.White)
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues).fillMaxWidth()
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(stateUi.value.currentNote) { note ->
                    NoteItem(
                        note = note,
                        onDoneClick = {
                            viewModel.update(note.copy(isFinished = true))
                        },
                        onDeleteClick = {
                            viewModel.delete(note)
                        },
                        onUndoneClick = {
                            viewModel.update(note.copy(isFinished = false))
                        }
                    )
                }
            }
        }
        if (showDialog) {
            DialogScreen(
                mainViewModel = viewModel,
                onDismissRequest = { showDialog = false }
            )
        }
    }
}
























