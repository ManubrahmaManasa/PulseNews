package com.example.pulsenews.presentation.articles_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pulsenews.domain.model.Article
import com.example.pulsenews.databinding.ArticleItemViewBinding
import com.example.pulsenews.presentation.extensions.loadImage

class ArticlesAdapter(private val articles: List<Article>):RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ArticleViewHolder {
        val binding = ArticleItemViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder:ArticleViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    override fun getItemCount(): Int = articles.size

    class ArticleViewHolder(private val binding: ArticleItemViewBinding) :RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article){
            binding.tvTitle.text = article.title
            binding.tvAuthor.text = article.author
            binding.tvDescription.text = article.description
            binding.tvPublishedAt.text = article.publishedAt
            binding.ivArticle.loadImage(article.urlToImage)
        }
    }
}