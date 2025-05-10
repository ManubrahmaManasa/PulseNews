package com.example.pulsenews.presentation.articles_list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private val _favourite = MutableStateFlow<List<Article>>(emptyList())
    val favourite:StateFlow<List<Article>> = _favourite

    private val _search = MutableLiveData<List<Article>>()
    val search:LiveData<List<Article>> = _search

    fun getSearchedArticleList(code:String){
        viewModelScope.launch {
            val result = repository.getSearchHeadlines(code)
            when(result){
                is NewsResult.Success -> {
                    Log.wtf("ArticleViewModel","getSearchedArticlesList: ${result.data}")
                    _search.postValue(result.data)
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
                        _favourite.update { result.data }
                    }
                    is NewsResult.Error -> {
                        _favourite.update { emptyList() }
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
            getArticleFromDB()
        }
    }

    fun clearAll(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.clearAll()
            getArticleFromDB()
        }
    }
}