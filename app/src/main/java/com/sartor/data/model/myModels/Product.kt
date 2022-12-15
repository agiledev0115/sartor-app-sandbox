package com.sartor.data.model.myModels

data class Product(
    val _id: String,
    val myBrands: MyBrands,
    val category: String,
    val created_at: String,
    val created_by: CreatedBy,
    val description: String,
    val img: Img,
    val name: String,
    val price: Int,
    val qty: Int,
    val rating: Int,
    val store: String
)