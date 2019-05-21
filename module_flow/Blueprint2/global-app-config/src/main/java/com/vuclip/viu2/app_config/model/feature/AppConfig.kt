package com.vuclip.viu2.app_config.model.feature


import com.google.gson.annotations.SerializedName

data class AppConfig(
    val app: App,
    @SerializedName("core_components")
    val coreComponents: CoreComponents,
    val features: List<Feature>
)