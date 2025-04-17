package com.example.pulsenews.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pulsenews.domain.models.Article

@Entity(tableName = "articles")
data class ArticleEntity(
    val title:String?,
    val description:String?,
    val urlToImage:String?,
    val publishedAt:String?,
    val author:String?,
    @PrimaryKey val url:String
)

fun List<ArticleEntity>.toListArticle(): List<Article> {
    return this.map {
        Article(
            title = it.title,
            description = it.description,
            urlToImage = it.urlToImage,
            publishedAt = it.publishedAt,
            author = it.author,
            url = it.url
        )
    }
}

fun ArticleEntity.toListArticle(): Article {
    return Article(
            title,
        description,
        urlToImage,
        publishedAt,author,url
        )
    }
