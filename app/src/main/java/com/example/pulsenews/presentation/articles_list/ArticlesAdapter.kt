package com.example.pulsenews.presentation.articles_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.pulsenews.R
import com.example.pulsenews.domain.models.Article
import com.example.pulsenews.databinding.ArticleItemViewBinding
import com.example.pulsenews.presentation.extensions.loadImage

class ArticlesAdapter(
    private var articles: List<Article>,
    private val onArticleClicked: (String) -> Unit,
    private val onArticleLongClicked: ((Article,Int)-> Unit)? = null
):RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ArticleViewHolder {
        val binding = ArticleItemViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder:ArticleViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    override fun getItemCount(): Int = articles.size

    inner class ArticleViewHolder(private val binding: ArticleItemViewBinding) :RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article){
            binding.tvTitle.text = article.title
            binding.tvAuthor.text = article.author
            binding.tvDescription.text = article.description
            binding.tvPublishedAt.text = article.publishedAt
            article.urlToImage?.let {
                binding.ivArticle.loadImage(it)
            }
            binding.root.setOnClickListener {
                onArticleClicked(article.url)
            }

            binding.root.setOnLongClickListener { view ->
                showPopUp(view,article)
                true
            }
        }
    }

    fun updateArticles(newList: List<Article>) {
        articles = newList
        notifyDataSetChanged()
    }

    private  fun showPopUp(view: View?, article: Article) {
        val popup = PopupMenu(view?.context, view)
        popup.inflate(R.menu.article_popup_menu)
        popup.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.add_to_favourites -> {
                    onArticleLongClicked?.let { it1 -> it1(article,R.id.add_to_favourites) }
                    true
                }
                R.id.remove_from_favourites -> {
                    onArticleLongClicked?.let { it1 -> it1(article, R.id.remove_from_favourites) }
                    true
                }

                else -> false
            }
        }
        popup.show()
    }
}