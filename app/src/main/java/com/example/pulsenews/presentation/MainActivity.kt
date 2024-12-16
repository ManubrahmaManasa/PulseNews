package com.example.pulsenews.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pulsenews.R
import com.example.pulsenews.databinding.ActivityMainBinding
import com.example.pulsenews.presentation.articles_list.ArticleFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private val searchFrag = SearchFrag()
    private val headLinesFrag = ArticleFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        supportFragmentManager.beginTransaction().replace(R.id.container,headLinesFrag).commit()

        mainBinding.bnvMain.setOnItemSelectedListener {menuItem ->
           when(menuItem.itemId){
                R.id.nav_search -> {
                    supportFragmentManager.beginTransaction().replace(R.id.container,searchFrag).commit()
                    true
                }
               R.id.nav_headlines -> {
                   supportFragmentManager.beginTransaction().replace(R.id.container,headLinesFrag).commit()
                   true
               }
               else -> false
            }

        }
    }
}