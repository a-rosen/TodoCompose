package com.example.todocompose.di

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todocompose.database.dao.TodoDao
import com.example.todocompose.database.models.TodoEntity
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)

@Database(entities = [TodoEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao

    // does this need @Binds, like in the repo module?
}