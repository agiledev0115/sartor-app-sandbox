package com.sartor.data.api.request

import com.google.gson.annotations.SerializedName

data class BlogRequest(

    @SerializedName("title")
    val title : String,
    @SerializedName("story")
    val story : String
)
