package com.example.pulsenews.data

import android.util.Log
import com.example.pulsenews.BuildConfig
import com.example.pulsenews.data.local.ArticleDao
import com.example.pulsenews.data.local.models.ArticleEntity
import com.example.pulsenews.data.local.models.toListArticle
import com.example.pulsenews.data.remote.toDomainArticles
import com.example.pulsenews.domain.ArticlesRepository
import com.example.pulsenews.domain.models.Article
import com.example.pulsenews.domain.models.toArticleEntity
import com.example.pulsenews.domain.utils.DataError
import com.example.pulsenews.domain.utils.NewsResult
import javax.inject.Inject

class ArticlesRepositoryImpl @Inject constructor(private val newsService: NewsService,
    private val articleDao: ArticleDao):ArticlesRepository {
    private val apiKey = BuildConfig.API_KEY
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

    override suspend fun getSearchHeadlines(searchCode:String): NewsResult<List<Article>, DataError.Network> {
        val response = newsService.getSearchHeadlines("",searchCode,apiKey)
        Log.d("SearchAPIRepoImpl", "result"+response)
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

    override suspend fun getArticlesFromDB(): NewsResult<List<Article>, DataError.Local> {
        val listFromDB = articleDao.getArticlesFromDB()
        return NewsResult.Success(listFromDB.toListArticle())
    }

    override suspend fun addToFavourites(article: Article) {
        articleDao.saveArticle(article.toArticleEntity())
    }

    override suspend fun removeFromFavourites(article: Article) {
        articleDao.removeArticle(article.toArticleEntity())
    }

    override suspend fun clearAll() {
        articleDao.clearAll()
    }
}