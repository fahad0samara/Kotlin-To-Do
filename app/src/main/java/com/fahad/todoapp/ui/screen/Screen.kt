package com.fahad.todoapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fahad.todoapp.R
import com.fahad.todoapp.domain.DISPLAY_TYPE
import com.fahad.todoapp.domain.MainViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen(viewModel: MainViewModel = hiltViewModel()) {
    val stateUi = viewModel.stateUi.value

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "To-Do List",
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                actions = {
                    IconButton(onClick = { viewModel.menuExpand.value = !viewModel.menuExpand.value }) {
                        Icon(
                            painter = painterResource(id = R.drawable.outline_filter_list_24),
                            tint = MaterialTheme.colorScheme.primary,
                            contentDescription = null)
                    }
                    DropdownMenu(
                        expanded = viewModel.menuExpand.value,
                        onDismissRequest = { viewModel.menuExpand.value = false }
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
                                    viewModel.menuExpand.value = false
                                }
                            )
                        }
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.showDialog.value = true },
                shape = RoundedCornerShape(50),
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = null, tint = Color.White)
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth(),
             contentAlignment = Alignment.Center



        ) {

            if (viewModel.stateUi.value.currentNote.isEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    // Display a message when there are no matching to-do items
                    Text(
                        text = when (viewModel.stateUi.value.displayType) {
                            DISPLAY_TYPE.ALL -> "No to-do items found. Press + to add a new note."
                            DISPLAY_TYPE.FINISHED -> "No finished to-do items found."
                            DISPLAY_TYPE.UNFINISHED -> "No unfinished to-do items found."
                            DISPLAY_TYPE.COMPLETED -> "No completed to-do items found."
                            DISPLAY_TYPE.UNCOMPLETED -> "No uncompleted to-do items found."
                        },

                        style = MaterialTheme.typography.bodyLarge,
textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.secondary,


)
                    Spacer(modifier = Modifier.height(0.dp))
                    Image(
                        painter = painterResource(id = R.drawable.home), // Replace with your image resource
                        contentDescription = null,
                        modifier = Modifier.size(200.dp).padding(bottom = 20.dp)// Adjust the size as needed
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(stateUi.currentNote) { note ->
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
        }
        if (viewModel.showDialog.value) {
            DialogScreen(
                mainViewModel = viewModel,
                onDismissRequest = { viewModel.showDialog.value = false }
            )
        }
    }
}


@Preview
@Composable
fun Add () {
    Screen()

}

























