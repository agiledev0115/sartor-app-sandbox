package com.sartor.data.model

data class CheckoutBillingDetails(
    val amount : Int,
    val billingAddress: String,
    val mobileNumber: String,
    val country: String,
    val state: String,
    val zipcode: Int,
    val cardName: String,
    val cardNumber: String,
    val cardExpireMonth: String,
    val cardExpireYear: String,
    val cvv: String,
)
