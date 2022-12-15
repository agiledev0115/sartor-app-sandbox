package com.sartor.data.model.myModels

data class BrandsItem(
    val _id: String,
    val created_at: String,
    val followers: List<Any>,
    val img: String,
    val is_top: Boolean,
    val likes: List<Any>,
    val title: String
)