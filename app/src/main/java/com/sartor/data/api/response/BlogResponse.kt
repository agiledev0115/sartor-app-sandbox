package com.sartor.data.api.response


import com.google.gson.annotations.SerializedName

data class BlogResponse(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("created_by")
    val createdBy: CreatedBy,
    @SerializedName("_id")
    val id: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("story")
    val story: String,
    @SerializedName("title")
    val title: String
) {
    data class CreatedBy(
        @SerializedName("archived")
        val archived: Boolean,
        @SerializedName("archived_at")
        val archivedAt: Any,
        @SerializedName("corporate_activation_status")
        val corporateActivationStatus: Boolean,
        @SerializedName("_id")
        val id: String,
        @SerializedName("internal")
        val `internal`: String,
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
    )
}