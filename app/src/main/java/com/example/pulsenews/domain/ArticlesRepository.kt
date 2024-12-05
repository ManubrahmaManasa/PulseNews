package com.example.pulsenews.domain

import com.example.pulsenews.domain.model.Article
import com.example.pulsenews.domain.utils.DataError
import com.example.pulsenews.domain.utils.NewsResult

interface ArticlesRepository {
    suspend fun getArticles(): NewsResult<List<Article>,DataError.Network>
}