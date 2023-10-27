package com.fahad.todoapp


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.fahad.todoapp.data.local.dto.LocalNote
import com.fahad.todoapp.domain.MainViewModel


@Composable
fun DialogScreen(onDismissRequest: () -> Unit,
                mainViewModel: MainViewModel
                 ) {
    Dialog(onDismissRequest = onDismissRequest) {
        var titel by remember {
            mutableStateOf("")
        }
        var description by remember {
            mutableStateOf("")
        }




        Column(modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(color = MaterialTheme.colorScheme.background)
            .padding(16.dp)
            .fillMaxWidth()

        ) {
            Text(text = "Add Note",
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(bottom = 8.dp)




                )
            TextField(
                value = titel,
                onValueChange = {
                    titel = it
                },
                placeholder = {
                    Text(text = "Enter Title")
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,

            )
            Spacer(modifier = Modifier.padding(8.dp))
            TextField(
                value = description,
                onValueChange = {
                    description = it
                },
                placeholder = {
                    Text(text = "Enter Description")
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,

                )
            Spacer(modifier = Modifier.padding(8.dp))

            Button(
                onClick = {
                    mainViewModel.insert(
                        LocalNote(
                            id = null,
                            title = titel,
                            description = description,
                            timestamp = System.currentTimeMillis(),
                            isPinned = false,
                            isArchived = false,
                            isFinished = false,
                            isSynced = false,
                            color = 0,
                            content = ""




                        )
                    )
                    onDismissRequest()








                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Add Note")
            }







        }


    }





}

