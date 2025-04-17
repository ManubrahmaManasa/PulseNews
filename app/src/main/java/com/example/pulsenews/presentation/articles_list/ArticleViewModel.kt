package com.example.pulsenews.presentation.articles_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pulsenews.domain.ArticlesRepository
import com.example.pulsenews.domain.models.Article
import com.example.pulsenews.domain.utils.NewsResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class ArticleViewModel @Inject constructor(private val repository: ArticlesRepository):ViewModel() {
    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val articles: StateFlow<List<Article>> = _articles

    fun getSearchedArticleList(code:String){
        viewModelScope.launch {
            val result = repository.getSearchHeadlines(code)
            Log.d("SearchAPIViewModel", "result"+result)
            when(result){
                is NewsResult.Success -> {
                    Log.wtf("ArticleViewModel","getSearchedArticlesList: ${result.data}")
                    _articles.update { result.data }
                }
                is NewsResult.Error -> {
                    Log.wtf("ArticleViewModel","getSearchedArticleList:Network error")
                }
            }
        }
    }

    fun getArticlesList(){
        viewModelScope.launch {
            when(val result = repository.getArticles()){
                is NewsResult.Success -> {
                    Log.wtf("ArticleViewModel","getArticlesList: ${result.data}")
                    _articles.update { result.data }
                }

                is NewsResult.Error -> {
                    Log.wtf("ArticleViewModel","getArticleList:Network error")
                }
            }
        }
    }

    fun getArticleFromDB(){
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getArticlesFromDB()
            withContext(Dispatchers.Main){
                when(result){
                    is NewsResult.Success -> {
                        _articles.update { result.data }
                    }
                    is NewsResult.Error -> {
                        _articles.update { emptyList() }
                    }
                }
            }
        }
    }

    fun addArticle(article: Article){
       viewModelScope.launch(Dispatchers.IO) {
           repository.addToFavourites(article)
       }
    }

    fun removeArticle(article: Article){
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeFromFavourites(article)
        }
    }

    fun clearAll(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.clearAll()
        }
    }
}