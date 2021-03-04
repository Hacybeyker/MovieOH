package com.hacybeyker.repository.network.exception

data class ApiException(
    val statusMessage: String,
    val success: Boolean,
    val statusCode: Int
) : Exception()