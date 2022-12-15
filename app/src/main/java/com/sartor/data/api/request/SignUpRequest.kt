package com.sartor.data.api.request

import com.google.gson.annotations.SerializedName

data class SignUpRequest(
   // username, password, full_name, user_type
    @SerializedName("username")
    val userName : String? = null,
    @SerializedName("password")
    val password : String? = null,
    @SerializedName("full_name")
    val fullName : String? = null,
    @SerializedName("user_type")
    val userType : String? = null,
    )
