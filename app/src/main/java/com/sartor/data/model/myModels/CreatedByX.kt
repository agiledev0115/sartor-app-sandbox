package com.sartor.data.model.myModels

data class CreatedByX(
    val _id: String,
    val archived: Boolean,
    val archived_at: Any,
    val corporate_activation_status: Boolean,
    val created_at: String,
    val `internal`: InternalX,
    val is_registered: Boolean,
    val role: String,
    val status: String,
    val username: String
)