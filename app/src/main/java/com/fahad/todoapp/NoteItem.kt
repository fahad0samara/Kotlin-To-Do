package com.fahad.todoapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.fahad.todoapp.data.local.dto.LocalNote

@Composable
fun NoteItem(
    note: LocalNote,
    onDeleteClick:()->Unit,
    onDoneClick:()->Unit,



    ) {
    Row (
        modifier = Modifier.padding(vertical = 18.dp).fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row (
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.size(width = 24.dp, height = 24.dp).clip(
                    RoundedCornerShape(50)
                )
                    .background(if (
                        note.isFinished
                    ) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.onSurface
                    })




            )
            Spacer(modifier = Modifier.size(12.dp))
            Column{
                Text(text = note.title,
                    fontWeight = FontWeight.Black,
                    maxLines = 1,
                    softWrap = false,
                    overflow = TextOverflow.Ellipsis,
                    )
                Text(text = note.content,
                    fontWeight = FontWeight.Normal,
                    maxLines = 1,
                    softWrap = false,
                    overflow = TextOverflow.Ellipsis,

                    )

            }

        }
        Row(
            modifier = Modifier.weight(1f),
        horizontalArrangement = Arrangement.End,

        ) {
            if (!note.isFinished){
                IconButton(onClick = onDoneClick) {
                    Icon(
                        imageVector = Icons.Filled.Done,
                        contentDescription = "Done",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }

            }

            IconButton(
                onClick = onDeleteClick) {
                Icon(
                    imageVector = Icons.Filled.Delete ,
                    contentDescription = "Delete",
                    tint = MaterialTheme.colorScheme.error

                )
            }




        }


    }





}