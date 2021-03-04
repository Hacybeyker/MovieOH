package com.hacybeyker.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val originalTitle: String,
    val title: String,
    val overview: String,
    val releaseDate: String,
    val voteAverage: Double,
    val posterPath: String,
    val backdropPath: String,
    val runtime: Int,
    val status: String
) : Parcelable {
    fun voteAverageString(): String {
        return if (voteAverage == 0.0) "" else voteAverage.toString()
    }

    fun runtimeString(): String {
        return if (runtime == 0) ""
        else {
            val hours: Int = runtime / 60
            val minutes: Int = runtime % 60
            "${hours}hr ${minutes}min"
        }
    }
}