package com.hacybeyker.usecases.repository.network

import com.hacybeyker.entities.Movie
import com.hacybeyker.entities.Pagination

interface IMovieRepositoryNetwork {
    suspend fun fetchMovie(movie: Int): Movie
    suspend fun fetchMoviePopular(page: Int): Pair<List<Movie>, Pagination>
    suspend fun fetchMovieSimilar(movie: Int): List<Movie>
}