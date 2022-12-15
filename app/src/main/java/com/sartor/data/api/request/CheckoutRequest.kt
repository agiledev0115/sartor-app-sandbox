package com.sartor.data.api.request

import com.google.gson.annotations.SerializedName

data class CheckoutRequest(

    @SerializedName("amount")
    val amount : Int,
    @SerializedName("billing")
    val billing : String,
    @SerializedName("mobile")
    val mobileNumber : String,
    @SerializedName("country")
    val country : String,
    @SerializedName("state")
    val state : String,
    @SerializedName("zipcode")
    val zipCode : Int,
    @SerializedName("cardname")
    val cardName : String,
    @SerializedName("cardnumber")
    val cardNumber : String,
    @SerializedName("cardExpirmont")
    val cardExpireMonth : String,
    @SerializedName("cardExpirYear")
    val cardExpireYear : String,
    @SerializedName("cvv")
    val cvv : String,







)
