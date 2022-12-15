package com.sartor.data.model

data class CartItem(
    val productName : String,
    val productImage : String,
    val productPrice : String,
    val productDescription : String,
    val productQuantity : Int

)

data class Cart(
    val cartItems: List<CartItem>,
    val total : String
)
