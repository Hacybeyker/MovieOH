package com.hacybeyker.movieoh.ui.movies

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hacybeyker.entities.Movie
import com.hacybeyker.entities.Pagination
import com.hacybeyker.usecases.usecase.MovieUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class MovieViewModel : ViewModel(), KoinComponent {

    private val movieUseCase: MovieUseCase by inject()

    private val movieMutableLiveData: MutableLiveData<List<Movie>> by lazy { MutableLiveData<List<Movie>>() }
    val movieLiveData: LiveData<List<Movie>> get() = movieMutableLiveData

    private val paginationMutableLiveData: MutableLiveData<Pagination> by lazy { MutableLiveData<Pagination>() }
    val paginationLiveData: LiveData<Pagination> get() = paginationMutableLiveData

    var loadingObserver: ObservableBoolean = ObservableBoolean(false)

    fun fetchMoviePopular(page: Int) = viewModelScope.launch(Dispatchers.IO) {
        try {
            loadingObserver.set(true)
            val response = movieUseCase.fetchMoviePopular(page = page)
            movieMutableLiveData.postValue(response.first)
            paginationMutableLiveData.postValue(response.second)
            loadingObserver.set(false)
        } catch (e: Exception) {
            loadingObserver.set(false)
            Log.e("TAG", "Here - fetchMovieUpcoming: ${e.message}")
        }
    }
}