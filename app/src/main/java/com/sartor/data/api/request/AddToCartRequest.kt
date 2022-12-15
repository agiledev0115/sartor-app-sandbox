package com.sartor.data.api.request

import com.google.gson.annotations.SerializedName

data class AddToCartRequest (
    @SerializedName("product")
    val productId : String
        )
