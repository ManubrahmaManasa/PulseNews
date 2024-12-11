package com.example.pulsenews.data

import com.example.pulsenews.data.model.Article
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("v2/top-headlines?country=us")
    suspend fun getHeadlines(@Query("apiKey")apiKey:String):Response<Article>
}