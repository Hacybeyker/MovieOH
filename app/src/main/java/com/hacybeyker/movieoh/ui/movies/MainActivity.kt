package com.hacybeyker.movieoh.ui.movies

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DefaultItemAnimator
import com.hacybeyker.entities.Movie
import com.hacybeyker.movieoh.R
import com.hacybeyker.movieoh.databinding.ActivityMainBinding
import com.hacybeyker.movieoh.ui.movies.adapter.MovieAdapter
import jp.wasabeef.recyclerview.animators.LandingAnimator
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), MovieAdapter.OnItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MovieViewModel by inject()
    private val adapter: MovieAdapter by lazy { MovieAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configBinding()
        configRecycler()
        observeLiveData()
    }

    private fun configBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.adapter = adapter
        binding.executePendingBindings()
    }

    private fun configRecycler() {
        binding.recyclerMovies.setHasFixedSize(true)
        binding.recyclerMovies.itemAnimator = DefaultItemAnimator()
        binding.recyclerMovies.itemAnimator = LandingAnimator().apply { addDuration = 400 }
    }

    private fun observeLiveData() {
        viewModel.fetchMovieUpcoming().observe(this) {
            it.let {
                adapter.items = it
            }
        }
    }

    override fun onItemSelected(item: Movie, view: View?) {
        Log.d("TAG", "Here - onItemSelected: ${item.title}")
    }
}