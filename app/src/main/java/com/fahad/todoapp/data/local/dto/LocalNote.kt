package com.fahad.todoapp.data.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class LocalNote(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    val isSynced: Boolean ,
    val description: String,
    val isPinned: Boolean ,
    val isArchived: Boolean ,
    val isFinished : Boolean ,



)
