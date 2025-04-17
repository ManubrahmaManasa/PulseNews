package com.example.pulsenews.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pulsenews.data.local.models.ArticleEntity

@Database(entities = [ArticleEntity::class], version = 1)
abstract class ArticleDatabase: RoomDatabase() {
    abstract fun getDao():ArticleDao
}