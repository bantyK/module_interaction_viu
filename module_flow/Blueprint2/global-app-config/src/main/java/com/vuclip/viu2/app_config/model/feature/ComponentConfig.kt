package com.vuclip.viu2.app_config.model.feature


import com.google.gson.annotations.SerializedName

data class ComponentConfig(
    val fallback: String,
    @SerializedName("read_device_id")
    val readDeviceId: Boolean
)