package com.fahad.todoapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.fahad.todoapp.data.local.dto.LocalNote
import kotlinx.coroutines.flow.Flow

@Dao
interface DAONote {
    @Insert
    suspend fun insert(note: LocalNote)

    @Query("SELECT * FROM notes")
    fun getNotes(): Flow<List<LocalNote>>

    @Query("SELECT * FROM notes WHERE isFinished=:isFinished")
    fun getFinishedNotes(isFinished: Boolean): Flow<List<LocalNote>>



    @Query("SELECT * FROM notes WHERE isSynced=:isSynced")
    fun getSyncedNotes(isSynced: Boolean): Flow<List<LocalNote>>

    @Delete
    suspend fun delete(note: LocalNote)

    @Update
    suspend fun update(note: LocalNote)
}
