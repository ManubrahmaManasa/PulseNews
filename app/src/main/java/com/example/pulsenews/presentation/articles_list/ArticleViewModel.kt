package com.example.pulsenews.presentation.articles_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pulsenews.domain.ArticlesRepository
import com.example.pulsenews.domain.model.Article
import com.example.pulsenews.domain.utils.NewsResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ArticleViewModel(private val repository: ArticlesRepository):ViewModel() {
    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val articles: StateFlow<List<Article>> = _articles

    init {
        getArticlesList()
    }

    private fun getArticlesList(){
        viewModelScope.launch {
            when(val result = repository.getArticles()){
                is NewsResult.Success -> {
                    _articles.update { result.data }
                }

                is NewsResult.Error -> {
                    Log.wtf("ArticleViewModel","getArticleList:Network error")
                }
            }
        }
    }
}