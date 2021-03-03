package com.hacybeyker.usecases.repository.network

import com.hacybeyker.entities.Movie

interface IMovieRepositoryNetwork {
    suspend fun fetchMovieUpcoming(): List<Movie>
}