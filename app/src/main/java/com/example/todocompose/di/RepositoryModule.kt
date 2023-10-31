package com.example.todocompose.di

import com.example.todocompose.database.InMemoryRepository
import com.example.todocompose.database.TodoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Dagger modules are instructions for how to create small pieces of the dependency graph.
 * It's abstract, which means I am allowed to declare abstract functions inside it. At
 * build-time, Dagger/Hilt will generate a class that extends this class, and has a real
 * implementation of all its abstract functions, which do the useful work of ferrying dependencies
 * to whoever requests them.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    /**
     * This function tells dagger how to create a TodoRepository. It's abstract, which means
     * I don't have to write a definition for it, but instead Dagger/Hilt will do annotation
     * processing magic to generate a version of this method (and class) that do the useful work.
     *
     * It requires as an argument an InMemoryRepository, for now. The generated code will be pretty
     * naive - it mostly says "oh, you want a TodoRepository? InMemoryRepository does that, and
     * I know how to make one, so here you go."
     */
    @Binds
    abstract fun howDoICreateATodoRepository(inMemoryRepository: InMemoryRepository) : TodoRepository
}
