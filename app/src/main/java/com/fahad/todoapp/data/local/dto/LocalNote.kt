package com.fahad.todoapp.data.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class LocalNote(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    val isSynced: Boolean = false,
    val description: String? = null,
    val isPinned: Boolean = false,
    val isArchived: Boolean = false,
    val isFinished : Boolean = false



)
