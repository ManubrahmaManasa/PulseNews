package com.example.pulsenews.presentation

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.pulsenews.R
import com.example.pulsenews.databinding.FragmentFavouritesBinding
import com.example.pulsenews.presentation.articles_list.ArticleViewModel
import com.example.pulsenews.presentation.articles_list.ArticlesAdapter
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FavouritesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavouritesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding:FragmentFavouritesBinding
    private val viewModel:ArticleViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentFavouritesBinding.inflate(layoutInflater)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getArticleFromDB()

        binding.clearAll.setOnClickListener {
            viewModel.clearAll()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.favourite.collect { articles ->
                    val adapter = ArticlesAdapter(
                        articles,
                        onArticleClicked = {url ->
                            val intent = Intent(requireContext(),ArticleDetailActivity::class.java)
                            intent.putExtra(ArticleDetailActivity.URL_KEY,url)
                            startActivity(intent)
                        },
                        onArticleLongClicked = { article,menuId ->
                        when (menuId) {
                            R.id.add_to_favourites -> {
                                viewModel.addArticle(article)
                            }

                            R.id.remove_from_favourites -> {
                                viewModel.removeArticle(article)
                            }
                        }
                    })
                    binding.rvArticles.adapter = adapter
                }
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FavouritesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FavouritesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}