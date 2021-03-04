package com.hacybeyker.movieoh.ui.movies

import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DefaultItemAnimator
import com.hacybeyker.entities.Movie
import com.hacybeyker.entities.Pagination
import com.hacybeyker.movieoh.R
import com.hacybeyker.movieoh.databinding.ActivityMainBinding
import com.hacybeyker.movieoh.ui.detail.DetailActivity
import com.hacybeyker.movieoh.ui.movies.adapter.MovieAdapter
import com.hacybeyker.movieoh.utils.EndLessRecycler
import com.hacybeyker.repository.network.exception.ApiException
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), MovieAdapter.OnItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MovieViewModel by inject()
    private val adapter: MovieAdapter by lazy { MovieAdapter(this) }
    private var countStart: Int = 1
    private lateinit var pagination: Pagination

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configBinding()
        configRecycler()
        observeLiveData()
        viewModel.fetchMoviePopular(countStart)
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
        binding.recyclerMovies.addOnScrollListener(object : EndLessRecycler() {
            override fun onLoadNextPage() {
                loadNexPage()
            }
        })
    }

    private fun loadNexPage() {
        if (!viewModel.loadingObserver.get() && (countStart <= this.pagination.totalPages)) {
            viewModel.fetchMoviePopular(page = ++countStart)
        }
    }

    private fun observeLiveData() {
        viewModel.movieLiveData.observe(this) {
            it?.let {
                adapter.addData(it)
            }
        }

        viewModel.paginationLiveData.observe(this) {
            it?.let {
                this.pagination = it
            }
        }

        viewModel.errorLiveData.observe(this) {
            it?.let { exception ->
                when (exception) {
                    is ApiException -> showError(message = exception.statusMessage)
                    else -> showError(getString(R.string.error_ocurred))
                }
            }
        }
    }

    private fun showError(message: String) {
        AlertDialog.Builder(ContextThemeWrapper(this, R.style.ThemeOverlay_AppCompat))
            .setTitle(R.string.app_name)
            .setCancelable(true)
            .setMessage(message)
            .setPositiveButton(getString(R.string.ok)) { dialogInterface, _ -> dialogInterface.dismiss() }
            .show()
    }

    override fun onItemSelected(item: Movie, view: View?) {
        DetailActivity.newInstance(activity = this, movie = item, image = view)
    }
}