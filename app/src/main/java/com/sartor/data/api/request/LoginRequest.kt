package com.sartor.data.api.request

import com.google.gson.annotations.SerializedName

data class LoginRequest(

    @SerializedName("username")
    val userName : String? = null,
    @SerializedName("password")
    val password : String? = null,
)
