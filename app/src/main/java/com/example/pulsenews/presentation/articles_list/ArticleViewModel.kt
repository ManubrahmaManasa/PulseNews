package com.example.pulsenews.presentation.articles_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide.init
import com.example.pulsenews.domain.ArticlesRepository
import com.example.pulsenews.domain.model.Article
import com.example.pulsenews.domain.utils.NewsResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ArticleViewModel @Inject constructor(private val repository: ArticlesRepository):ViewModel() {
    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val articles: StateFlow<List<Article>> = _articles

    init {
        getArticlesList()
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
}