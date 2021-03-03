package com.hacybeyker.repository.network.services

import com.hacybeyker.repository.network.model.response.BaseResponse
import com.hacybeyker.repository.network.model.response.MovieResponse
import retrofit2.Response
import retrofit2.http.POST

interface MovieServices {
    @POST("movie/upcoming")
    suspend fun fetchMovieUpcoming(): Response<BaseResponse<List<MovieResponse>>>
}