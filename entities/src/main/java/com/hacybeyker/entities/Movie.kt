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
        return voteAverage.toString()
    }

    fun runtimeString(): String {
        runtime.let {
            val hours: Int = it / 60
            val minutes: Int = it % 60
            return "${hours}hr ${minutes}min"
        }
    }
}