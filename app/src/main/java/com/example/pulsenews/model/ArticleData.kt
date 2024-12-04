package com.example.pulsenews.model

/*whenever there is a network call or fetching data from server start working on Data class first
*which fields need to be fetched by observice the JSON data mentioned in the API*/
data class ArticleData(
    val title:String,
    val description:String,
    val urlToImage:String,
    val content:String,
    val author:String
)
