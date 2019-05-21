package com.vuclip.viu2.app_config.model.feature


import com.google.gson.annotations.SerializedName

data class ComponentInfo(
    val desc: String,
    @SerializedName("NOTE")
    val nOTE: String
)