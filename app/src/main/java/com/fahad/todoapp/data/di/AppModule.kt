package com.fahad.todoapp.data.di

import android.content.Context
import androidx.room.Room
import com.fahad.todoapp.data.local.DAONote
import com.fahad.todoapp.data.local.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideDatebase(@ApplicationContext context: Context): Database {
        return Room.databaseBuilder(
            context.applicationContext,
            Database::class.java,
            "note_database"


        ).build()
    }
    @Provides
    fun provideDao(database: Database) : DAONote = database.noteDao()
}