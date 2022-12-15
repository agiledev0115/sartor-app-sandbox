package com.sartor.data.model.myModels

data class Customer(
    val _id: String,
    val created_at: String,
    val email: String,
    val favorite: List<String>,
    val full_name: String,
    val picture: String,
    val preference: Boolean,
    val reviews: List<Any>,
    val updated_at: Any,
    val user: String
)