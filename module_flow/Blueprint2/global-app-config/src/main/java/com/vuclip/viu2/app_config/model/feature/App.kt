package com.vuclip.viu2.app_config.model.feature


import com.google.gson.annotations.SerializedName

data class App(

    @SerializedName("app_config")
    val appConfig: AppConfigX,

    @SerializedName("app_features")
    val appFeatures: List<String>,

    @SerializedName("app_info")
    val appInfo: AppInfo
)