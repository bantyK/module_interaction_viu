package com.vuclip.viu2.app_config.model.feature


import com.google.gson.annotations.SerializedName

data class AppInfo(
    @SerializedName("app_id")
    val appId: String,
    @SerializedName("app_name")
    val appName: String
)