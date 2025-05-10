package com.example.pulsenews.di

import android.content.Context
import androidx.room.Room
import com.example.pulsenews.data.local.ArticleDao
import com.example.pulsenews.data.local.ArticleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context):ArticleDatabase {
        return Room.databaseBuilder(
                context,
                ArticleDatabase::class.java,
        "article_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideArticleDao(articleDatabase: ArticleDatabase):ArticleDao {
        return articleDatabase.getDao()
    }
}