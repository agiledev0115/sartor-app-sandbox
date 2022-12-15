package com.sartor.data.model.myModels

data class Brands(
    val _id: String,
    val created_at: String,
    val followers: List<String>,
    val img: String,
    val is_top: Boolean,
    val likes: List<Any>,
    val title: String
)