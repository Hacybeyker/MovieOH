package com.hacybeyker.movieoh.ui.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.hacybeyker.usecases.usecase.MovieUseCase
import kotlinx.coroutines.Dispatchers
import org.koin.core.KoinComponent
import org.koin.core.inject

class DetailViewModel : ViewModel(), KoinComponent {

    private val movieUseCase: MovieUseCase by inject()

    fun fetchMovie(movie: Int) = liveData(Dispatchers.IO) {
        try {
            val response = movieUseCase.fetchMovie(movie = movie)
            emit(response)
        } catch (e: Exception) {
            Log.e("TAG", "Here - fetchMovieUpcoming: ${e.message}")
        }
    }

    fun fetchMovieSimilar(movie: Int) = liveData(Dispatchers.IO) {
        try {
            val response = movieUseCase.fetchMovieSimilar(movie = movie)
            emit(response)
        } catch (e: Exception) {
            Log.e("TAG", "Here - fetchMovieUpcoming: ${e.message}")
        }
    }
}