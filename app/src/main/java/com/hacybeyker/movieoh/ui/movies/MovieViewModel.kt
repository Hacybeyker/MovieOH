package com.hacybeyker.movieoh.ui.movies

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.hacybeyker.usecases.usecase.MovieUseCase
import kotlinx.coroutines.Dispatchers
import org.koin.core.KoinComponent
import org.koin.core.inject

class MovieViewModel : ViewModel(), KoinComponent {

    private val movieUseCase: MovieUseCase by inject()

    /*private val movieUpcoming: MutableLiveData<List<Movie>> by lazy { MutableLiveData<List<Movie>>() }
    val movieUpcomingLiveData: LiveData<List<Movie>> get() = movieUpcoming*/

    fun fetchMovieUpcoming() = liveData(Dispatchers.IO) {
        try {
            val response = movieUseCase.fetchMovieUpcoming()
            emit(response)
        } catch (e: Exception) {
            Log.e("TAG", "Here - fetchMovieUpcoming: ${e.message}")
        }
    }
}