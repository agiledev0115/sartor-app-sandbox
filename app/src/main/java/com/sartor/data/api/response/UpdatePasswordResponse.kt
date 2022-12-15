package com.sartor.data.api.response


import com.google.gson.annotations.SerializedName

data class UpdatePasswordResponse(
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
)