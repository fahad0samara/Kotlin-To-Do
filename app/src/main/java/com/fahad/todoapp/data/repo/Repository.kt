package com.fahad.todoapp.data.repo

import com.fahad.todoapp.data.local.DAONote
import com.fahad.todoapp.data.local.dto.LocalNote
import javax.inject.Inject

class Repository @Inject constructor(
    private val daoNote: DAONote
)
{
    suspend fun insert (note: LocalNote) = daoNote.insert(note)
    fun getNotes() = daoNote.getNotes()
    suspend fun getFinishedNotes(isFinished: Boolean) = daoNote.getFinishedNotes(isFinished)


    suspend fun getSyncedNotes(isSynced: Boolean) = daoNote.getSyncedNotes(isSynced)
    suspend fun delete(note: LocalNote) = daoNote.delete(note)
    suspend fun update(note: LocalNote) = daoNote.update(note)

}
