package com.example.pulsenews.data

import com.example.pulsenews.BuildConfig
import com.example.pulsenews.data.model.toDomainArticles
import com.example.pulsenews.domain.ArticlesRepository
import com.example.pulsenews.domain.model.Article
import com.example.pulsenews.domain.utils.DataError
import com.example.pulsenews.domain.utils.NewsResult
import javax.inject.Inject

class ArticlesRepositoryImpl @Inject constructor(private val newsService: NewsService):ArticlesRepository {
    val apiKey = BuildConfig.API_KEY
    override suspend fun getArticles(): NewsResult<List<Article>, DataError.Network> {
        val response = newsService.getHeadlines(apiKey)

        if(response.isSuccessful){
            val articles = response.body()
            return if(articles != null){
                NewsResult.Success(articles.toDomainArticles())
            }else{
                NewsResult.Success(emptyList())
            }
        }else{
            return NewsResult.Error(DataError.Network.UNKNOWN)
        }
    }
}