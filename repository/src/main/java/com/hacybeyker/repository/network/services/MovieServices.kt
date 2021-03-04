package com.hacybeyker.repository.network.services

import com.hacybeyker.repository.network.model.response.BaseResponse
import com.hacybeyker.repository.network.model.response.MovieResponse
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Path

interface MovieServices {

    @POST("movie/{movie}")
    suspend fun fetchMovie(@Path(value = "movie") movie: Int): Response<MovieResponse>

    @POST("movie/upcoming")
    suspend fun fetchMovieUpcoming(): Response<BaseResponse<List<MovieResponse>>>

    @POST("movie/{movie}/similar")
    suspend fun fetchMovieSimilar(@Path(value = "movie") movie: Int): Response<BaseResponse<List<MovieResponse>>>
}