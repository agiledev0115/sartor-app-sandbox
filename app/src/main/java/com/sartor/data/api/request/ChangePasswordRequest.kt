package com.sartor.data.api.request

import com.google.gson.annotations.SerializedName

data class ChangePasswordRequest(


    @SerializedName("old_password")
    val oldPassword : String? = null,
    @SerializedName("new_password")
    val newPassword : String? = null,
)
