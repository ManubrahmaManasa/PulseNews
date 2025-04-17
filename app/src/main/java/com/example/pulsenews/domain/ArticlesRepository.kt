package com.example.pulsenews.domain

import com.example.pulsenews.data.local.models.ArticleEntity
import com.example.pulsenews.domain.models.Article
import com.example.pulsenews.domain.utils.DataError
import com.example.pulsenews.domain.utils.NewsResult

interface ArticlesRepository {
    suspend fun getArticles(): NewsResult<List<Article>,DataError.Network>
    suspend fun getSearchHeadlines(searchCode:String):NewsResult<List<Article>,DataError.Network>
    suspend fun getArticlesFromDB():NewsResult<List<Article>,DataError.Local>
    suspend fun addToFavourites(article: Article)
    suspend fun removeFromFavourites(article: Article)
    suspend fun clearAll()
}