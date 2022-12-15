package com.sartor.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


data class Product(
    var name: String,
    var price: String,
    var store: String,
    var img: String,
    var id:String
)

@Parcelize
data class ProductDetails(
    var storeName : String,
    var storeImage: String,
    var productName: String,
    var productPrice: Int,
    var productRating: Int,
    var ProductDetails: String,
    var productImages : List<String>,
    var productId : String
): Parcelable