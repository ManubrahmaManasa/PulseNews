package com.example.pulsenews.presentation

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.pulsenews.R
import com.example.pulsenews.databinding.ActivityArticleDetailBinding
import com.example.pulsenews.domain.models.Article
import com.example.pulsenews.presentation.articles_list.ArticleViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ArticleDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArticleDetailBinding
    private val viewModel: ArticleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityArticleDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val url = intent.getStringExtra(ArticleDetailActivity.URL_KEY) ?: ""

        binding.wvDetail.apply {
            settings.javaScriptEnabled = true
            webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    binding.pbLoader.visibility = View.VISIBLE
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    binding.pbLoader.visibility = View.GONE
                }
            }
            loadUrl(url)
        }

        /*var isFav = false
        binding.fabFav.apply {
            setOnClickListener {
                isFav = !isFav
                if(isFav) {
                    setImageResource(R.drawable.ic_favourite_filled)
                    viewModel.addArticle(Article("Manu","How are you","","today","Dev","https://www.google.com/"))
                } else {
                    setImageResource(R.drawable.ic_favorite_border)
                    //viewModel.removeArticle()
                }
            }
        }*/
    }

    companion object {
        const val URL_KEY = "url"
    }
}