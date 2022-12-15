package com.sartor.data.api.response


import com.google.gson.annotations.SerializedName

data class CartResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("total")
    val total: String
) {
    data class Data(
        @SerializedName("created_at")
        val createdAt: String,
        @SerializedName("created_by")
        val createdBy: CreatedBy,
        @SerializedName("customer")
        val customer: Customer,
        @SerializedName("_id")
        val id: String,
        @SerializedName("product")
        val product: Product
    ) {
        data class CreatedBy(
            @SerializedName("archived")
            val archived: Boolean,
            @SerializedName("archived_at")
            val archivedAt: Any,
            @SerializedName("corporate_activation_status")
            val corporateActivationStatus: Boolean,
            @SerializedName("customers")
            val customers: String,
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
            @SerializedName("updated_at")
            val updatedAt: String,
            @SerializedName("username")
            val username: String
        )

        data class Customer(
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

        data class Product(
            @SerializedName("brands")
            val brands: Brands,
            @SerializedName("category")
            val category: Category,
            @SerializedName("created_at")
            val createdAt: String,
            @SerializedName("created_by")
            val createdBy: String,
            @SerializedName("description")
            val description: String,
            @SerializedName("_id")
            val id: String,
            @SerializedName("img")
            val img: String,
            @SerializedName("name")
            val name: String,
            @SerializedName("price")
            val price: Int,
            @SerializedName("qty")
            val qty: Int,
            @SerializedName("rating")
            val rating: Int,
            @SerializedName("store")
            val store: String
        ) {
            data class Brands(
                @SerializedName("created_at")
                val createdAt: String,
                @SerializedName("followers")
                val followers: List<Any>,
                @SerializedName("_id")
                val id: String,
                @SerializedName("img")
                val img: String,
                @SerializedName("is_top")
                val isTop: Boolean,
                @SerializedName("likes")
                val likes: List<Any>,
                @SerializedName("title")
                val title: String
            )

            data class Category(
                @SerializedName("archived")
                val archived: Boolean,
                @SerializedName("archived_at")
                val archivedAt: Any,
                @SerializedName("created_at")
                val createdAt: String,
                @SerializedName("created_by")
                val createdBy: String,
                @SerializedName("_id")
                val id: String,
                @SerializedName("name")
                val name: String,
                @SerializedName("updated_at")
                val updatedAt: Any
            )
        }
    }
}