package com.sartor.data.api.response

import com.google.gson.annotations.SerializedName

data class CheckoutResponse(

    @SerializedName("msg")
    val message: String,
    @SerializedName("status")
    val status: Int

    )
