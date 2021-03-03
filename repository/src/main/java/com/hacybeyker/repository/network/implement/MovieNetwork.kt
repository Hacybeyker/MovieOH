package com.hacybeyker.repository.network.implement

import com.hacybeyker.entities.Movie
import com.hacybeyker.repository.network.model.response.MovieResponse
import com.hacybeyker.repository.network.services.MovieServices
import com.hacybeyker.usecases.repository.network.IMovieRepositoryNetwork
import org.koin.core.KoinComponent
import org.koin.core.inject

class MovieNetwork : IMovieRepositoryNetwork, KoinComponent {

    private val services: MovieServices by inject()

    override suspend fun fetchMovieUpcoming(): List<Movie> {
        val response = services.fetchMovieUpcoming()
        val movies: ArrayList<Movie>? by lazy { arrayListOf() }
        if (response.isSuccessful) {
            response.body()?.apply {
                movies?.addAll(MovieResponse.toMovies(this.result))
            }
        }
        return movies ?: throw Exception("Helouda")
    }
}