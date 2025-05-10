package com.example.pulsenews.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pulsenews.data.local.models.ArticleEntity

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveArticle(article: ArticleEntity)

    @Query("SELECT * FROM articles")
    fun getArticlesFromDB():List<ArticleEntity>

    @Delete
    fun removeArticle(article: ArticleEntity)

    @Query("DELETE FROM articles") // Replace "articles" with your table name
    suspend fun clearAll()
}