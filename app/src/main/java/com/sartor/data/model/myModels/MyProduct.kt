package com.sartor.data.model.myModels

data class MyProduct(
    val _id: String,
    val brands: Brands,
    val category: String,
    val created_at: String,
    val created_by: CreatedByX,
    val description: String,
    val img: ImgX,
    val name: String,
    val price: Int,
    val qty: Int,
    val rating: Int,
    val store: String
)