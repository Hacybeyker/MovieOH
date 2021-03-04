package com.hacybeyker.repository.network.model.response

import com.google.gson.annotations.SerializedName
import com.hacybeyker.repository.network.exception.ApiException

data class ErrorResponse(
    @SerializedName("status_message")
    var statusMessage: String = "Error",
    @SerializedName("success")
    var success: Boolean = false,
    @SerializedName("status_code")
    var statusCode: Int = 0
) {
    fun getApiException(): ApiException =
        ApiException(
            statusMessage = statusMessage,
            success = success,
            statusCode = statusCode
        )

}