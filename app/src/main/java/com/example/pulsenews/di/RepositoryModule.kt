package com.example.pulsenews.di

import com.example.pulsenews.data.ArticlesRepositoryImpl
import com.example.pulsenews.domain.ArticlesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindRepository(repositoryImpl:ArticlesRepositoryImpl):ArticlesRepository
}