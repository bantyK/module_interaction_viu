package com.vuclip.viu2.app_config.model.feature


import com.google.gson.annotations.SerializedName

data class AppConfigX(
    @SerializedName("launch_feature")
    val launchFeature: String,
    @SerializedName("NOTE")
    val nOTE: String
)