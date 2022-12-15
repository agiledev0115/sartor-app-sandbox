package com.sartor.data.api.response


import com.google.gson.annotations.SerializedName

data class BrandResponse(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("followers")
    val followers: List<Any>,
    @SerializedName("_id")
    val id: String,
    @SerializedName("img")
    val img: String,
    @SerializedName("is_top")
    val isTop: Boolean,
    @SerializedName("likes")
    val likes: List<Any>,
    @SerializedName("title")
    val title: String
)