package com.fahad.todoapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fahad.todoapp.data.local.dto.LocalNote

@Database(
    entities=[LocalNote::class],
    version = 1

)

abstract class Database: RoomDatabase() {
    abstract fun noteDao(): DAONote
}