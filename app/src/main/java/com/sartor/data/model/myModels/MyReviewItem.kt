package com.sartor.data.model.myModels

data class MyReviewItem(
    val _id: String,
    val created_at: String,
    val created_by: CreatedByXXXX,
    val customer: CustomerX,
    val img: String,
    val product: ProductXX,
    val rate: Int,
    val review: String
)