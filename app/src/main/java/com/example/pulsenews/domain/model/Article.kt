package com.example.pulsenews.domain.model

/*whenever there is a network call or fetching data from server start working on Data class first
*which fields need to be fetched by observice the JSON data mentioned in the API*/
data class Article(
    val title:String?,
    val description:String?,
    val urlToImage:String?,
    val publishedAt:String?,
    val author:String?,
    val url:String
)
