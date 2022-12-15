package com.sartor.data.model.myModels

data class User(
    val _id: String,
    val archived: Boolean,
    val archived_at: Any,
    val corporate_activation_status: Boolean,
    val is_registered: Boolean,
    val last_login: String,
    val status: String
)