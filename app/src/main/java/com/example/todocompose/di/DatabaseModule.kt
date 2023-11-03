package com.example.todocompose.di

import android.content.Context
import androidx.room.Room
import com.example.todocompose.database.TodoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    fun provideDatabase(context: Context): TodoDatabase {
        return Room
            .databaseBuilder(
                context,
                TodoDatabase::class.java,
                "todo-database"
            )
            .build()
    }

    @Provides
    @Singleton
    fun todoDao(
        database: TodoDatabase
    ) = database.todoDao()
}