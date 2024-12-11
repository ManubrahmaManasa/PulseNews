package com.example.pulsenews.data

import com.example.pulsenews.data.model.NewsService
import com.example.pulsenews.domain.ArticlesRepository
import com.example.pulsenews.domain.model.Article
import com.example.pulsenews.domain.utils.DataError
import com.example.pulsenews.domain.utils.NewsResult

class ArticlesRepositoryImpl(private val newsService: NewsService):ArticlesRepository {
    override suspend fun getArticles(): NewsResult<List<Article>, DataError.Network> {
        return NewsResult.Success(emptyList())
    }
}