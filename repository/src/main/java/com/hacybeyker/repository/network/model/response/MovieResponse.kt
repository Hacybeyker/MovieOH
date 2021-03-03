package com.hacybeyker.repository.network.model.response

import com.google.gson.annotations.SerializedName
import com.hacybeyker.entities.Movie

data class MovieResponse(
    @SerializedName("id")
    val id: String?,
    @SerializedName("original_title")
    val originalTitle: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("backdrop_path")
    val backdropPath: String?
) {
    private fun toMovie(): Movie {
        return Movie(
            id = id ?: "",
            originalTitle = originalTitle ?: "",
            title = title ?: "",
            overview = overview ?: "",
            releaseDate = releaseDate ?: "",
            voteAverage = voteAverage ?: 0.0,
            posterPath = posterPath ?: "",
            backdropPath = backdropPath ?: ""
        )
    }

    companion object {
        fun toMovies(movies: List<MovieResponse>): List<Movie> {
            val movieList = arrayListOf<Movie>()
            for (item in movies)
                movieList.add(item.toMovie())
            return movieList
        }
    }
}