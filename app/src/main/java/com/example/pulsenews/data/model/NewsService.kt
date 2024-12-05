package com.example.pulsenews.data.model

import com.example.pulsenews.domain.model.Article
import retrofit2.http.GET
import retrofit2.http.Path

interface NewsService {

    @GET("v2/top-headlines?country={country}")
    suspend fun getHeadlines(@Path("country")countryName:String):List<Article>
}