package com.example.pulsenews.data

import com.example.pulsenews.data.model.ArticleDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("top-headlines?country=us")
    suspend fun getHeadlines(@Query("apiKey")apiKey:String):Response<ArticleDTO>

    @GET("everything?")
    suspend fun getSearchHeadlines(@Query("q")searchCode: String,@Query("apiKey")apiKey: String):Response<ArticleDTO>
}