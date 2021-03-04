package com.hacybeyker.usecases.repository.network

import com.hacybeyker.entities.Movie

interface IMovieRepositoryNetwork {
    suspend fun fetchMovie(movie: Int): Movie
    suspend fun fetchMovieUpcoming(): List<Movie>
    suspend fun fetchMovieSimilar(movie: Int): List<Movie>
}