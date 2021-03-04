package com.hacybeyker.movieoh.ui.detail

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Pair
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import com.hacybeyker.entities.Movie
import com.hacybeyker.movieoh.R
import com.hacybeyker.movieoh.databinding.ActivityDetailBinding
import com.hacybeyker.movieoh.ui.movies.adapter.MovieAdapter

class DetailActivity : AppCompatActivity(), MovieAdapter.OnItemSelectedListener {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by lazy {
        ViewModelProvider(this@DetailActivity).get(DetailViewModel::class.java)
    }
    private val adapterSimilar: MovieAdapter by lazy { MovieAdapter(this) }
    private lateinit var movie: Movie

    companion object {
        fun newInstance(activity: Activity, movie: Movie, image: View?) {
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(Movie::class.java.name, movie)
            if (image != null) {
                ViewCompat.setTransitionName(image, movie.id.toString())
                val p1 =
                    Pair.create<View, String>(image, ViewCompat.getTransitionName(image))
                val options = ActivityOptions.makeSceneTransitionAnimation(activity, p1)
                activity.startActivity(intent, options.toBundle())
            } else
                activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getIntentData()
        configBinding()
        configRecycler()
        observeLiveData()
        binding.imageMoviePosterFront.transitionName = movie.id.toString()
        title = movie.title
    }

    private fun observeLiveData() {
        viewModel.fetchMovie(movie = movie.id).observe(this) {
            it?.let {
                this.movie = it
                binding.textMovieRuntime.text = it.runtimeString()
                binding.textMovieStatus.text = it.status
            }
        }

        viewModel.fetchMovieSimilar(movie = movie.id).observe(this) {
            it?.let {
                if (it.isEmpty())
                    binding.constraintMovieSimilar.visibility = View.GONE
                else
                    adapterSimilar.addData(it)
            }
        }
    }

    private fun configRecycler() {
        binding.recyclerMoviesSimilar.setHasFixedSize(true)
        binding.recyclerMoviesSimilar.itemAnimator = DefaultItemAnimator()
    }

    private fun getIntentData() {
        intent?.let { it ->
            val movie = it.getParcelableExtra<Movie>(Movie::class.java.name)
            this.movie = movie as Movie
        }
    }

    private fun configBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        binding.lifecycleOwner = this
        binding.adapterSimilar = adapterSimilar
        binding.movie = movie
        binding.executePendingBindings()
    }

    override fun onItemSelected(item: Movie, view: View?) {
    }
}