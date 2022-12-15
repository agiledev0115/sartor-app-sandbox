package com.sartor.data.api.request

import com.google.gson.annotations.SerializedName

data class ReviewRequest(

    @SerializedName("product")
    val product : String,
    @SerializedName("review")
    val review : String,
    @SerializedName("rate")
    val rate : String,
    @SerializedName("uploaded_image")
    val image : String
)
