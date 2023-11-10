package com.example.todocompose.di

import android.content.Context
import androidx.room.Room
import com.example.todocompose.database.TodoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): TodoDatabase {
        return Room
            .databaseBuilder(
                context,
                TodoDatabase::class.java,
                "todo-database"
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun todoDao(
        database: TodoDatabase
    ) = database.todoDao()
}