package com.sartor.data.api.response


import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("brands")
    val brands: Brands,
    @SerializedName("category")
    val category: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("created_by")
    val createdBy: CreatedBy,
    @SerializedName("description")
    val description: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("img")
    val img: Img,
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

    data class CreatedBy(
        @SerializedName("archived")
        val archived: Boolean,
        @SerializedName("archived_at")
        val archivedAt: Any,
        @SerializedName("corporate_activation_status")
        val corporateActivationStatus: Boolean,
        @SerializedName("created_at")
        val createdAt: String,
        @SerializedName("_id")
        val id: String,
        @SerializedName("internal")
        val `internal`: Internal,
        @SerializedName("is_registered")
        val isRegistered: Boolean,
        @SerializedName("role")
        val role: String,
        @SerializedName("status")
        val status: String,
        @SerializedName("username")
        val username: String
    ) {
        data class Internal(
            @SerializedName("archived_at")
            val archivedAt: Any,
            @SerializedName("brands")
            val brands: String,
            @SerializedName("created_at")
            val createdAt: String,
            @SerializedName("first_name")
            val firstName: String,
            @SerializedName("_id")
            val id: String,
            @SerializedName("last_name")
            val lastName: String,
            @SerializedName("picture")
            val picture: String,
            @SerializedName("updated_at")
            val updatedAt: Any,
            @SerializedName("user")
            val user: String
        )
    }

    data class Img(
        @SerializedName("created_at")
        val createdAt: String,
        @SerializedName("created_by")
        val createdBy: String,
        @SerializedName("_id")
        val id: String,
        @SerializedName("img0")
        val img0: String,
        @SerializedName("img1")
        val img1: String,
        @SerializedName("img2")
        val img2: String,
        @SerializedName("img3")
        val img3: String,
        @SerializedName("img4")
        val img4: String,
        @SerializedName("img5")
        val img5: String,
        @SerializedName("img6")
        val img6: String,
        @SerializedName("img7")
        val img7: String
    )
}