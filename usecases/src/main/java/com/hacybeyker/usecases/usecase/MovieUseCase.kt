package com.hacybeyker.usecases.usecase

import com.hacybeyker.entities.Movie
import com.hacybeyker.entities.Pagination
import com.hacybeyker.usecases.repository.network.IMovieRepositoryNetwork
import org.koin.core.KoinComponent
import org.koin.core.inject

class MovieUseCase : KoinComponent {
    private val iMovieUseCase: IMovieRepositoryNetwork by inject()

    suspend fun fetchMovie(movie: Int): Movie {
        return iMovieUseCase.fetchMovie(movie = movie)
    }

    suspend fun fetchMoviePopular(page: Int): Pair<List<Movie>, Pagination> {
        return iMovieUseCase.fetchMoviePopular(page = page)
    }

    suspend fun fetchMovieSimilar(movie: Int): List<Movie> {
        return iMovieUseCase.fetchMovieSimilar(movie = movie)
    }
}