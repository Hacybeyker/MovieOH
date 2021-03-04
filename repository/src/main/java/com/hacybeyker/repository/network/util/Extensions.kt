package com.hacybeyker.repository.network.util

import com.google.gson.Gson
import com.hacybeyker.repository.network.model.response.ErrorResponse
import okhttp3.ResponseBody

fun ResponseBody?.toErrorResponse(): ErrorResponse {
    return this?.let {
        return Gson().fromJson(it.string(), ErrorResponse::class.java)
    } ?: ErrorResponse()
}