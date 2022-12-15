package com.sartor.data.api.response


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("token")
    val token: String,
    @SerializedName("user")
    val user: User
) {
    data class User(
        @SerializedName("archived")
        val archived: Boolean,
        @SerializedName("archived_at")
        val archivedAt: Any,
        @SerializedName("corporate_activation_status")
        val corporateActivationStatus: Boolean,
        @SerializedName("customers")
        val customers: Customers,
        @SerializedName("_id")
        val id: String,
        @SerializedName("is_registered")
        val isRegistered: Boolean,
        @SerializedName("last_login")
        val lastLogin: String,
        @SerializedName("role")
        val role: String,
        @SerializedName("status")
        val status: String,
        @SerializedName("username")
        val username: String
    ) {
        data class Customers(
            @SerializedName("created_at")
            val createdAt: String,
            @SerializedName("email")
            val email: String,
            @SerializedName("favorite")
            val favorite: List<Any>,
            @SerializedName("full_name")
            val fullName: String,
            @SerializedName("_id")
            val id: String,
            @SerializedName("picture")
            val picture: String,
            @SerializedName("preference")
            val preference: Boolean,
            @SerializedName("reviews")
            val reviews: List<Any>,
            @SerializedName("updated_at")
            val updatedAt: Any,
            @SerializedName("user")
            val user: String
        )
    }
}