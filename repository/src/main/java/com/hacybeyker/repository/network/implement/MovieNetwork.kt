package com.hacybeyker.repository.network.implement

import com.hacybeyker.entities.Movie
import com.hacybeyker.entities.Pagination
import com.hacybeyker.repository.network.model.response.MovieResponse
import com.hacybeyker.repository.network.services.MovieServices
import com.hacybeyker.usecases.repository.network.IMovieRepositoryNetwork
import org.koin.core.KoinComponent
import org.koin.core.inject

class MovieNetwork : IMovieRepositoryNetwork, KoinComponent {

    private val services: MovieServices by inject()

    override suspend fun fetchMovie(movie: Int): Movie {
        val response = services.fetchMovie(movie = movie)
        var movieObject: Movie? = null
        if (response.isSuccessful) {
            response.body()?.apply {
                movieObject = this.toMovie()
            }
        }
        return movieObject ?: throw Exception("Helouda")
    }

    override suspend fun fetchMoviePopular(page: Int): Pair<List<Movie>, Pagination> {
        val response = services.fetchMoviePopular(page = page)
        var movies: Pair<List<Movie>, Pagination>? = null
        if (response.isSuccessful) {
            response.body()?.apply {
                movies = Pair(
                    MovieResponse.toMovies(this.result),
                    Pagination(this.page, this.totalPages)
                )
            }
        }
        return movies ?: throw Exception("Helouda")
    }

    override suspend fun fetchMovieSimilar(movie: Int): List<Movie> {
        val response = services.fetchMovieSimilar(movie = movie)
        val movies: ArrayList<Movie>? by lazy { arrayListOf() }
        if (response.isSuccessful) {
            response.body()?.apply {
                movies?.addAll(MovieResponse.toMovies(this.result))
            }
        }
        return movies ?: throw Exception("Helouda")
    }
}