package com.vuclip.viu2.app_config.model.feature


import com.google.gson.annotations.SerializedName

data class CoreComponents(
    val debug: Debug,
    val http: Http,
    val infra: Infra
)