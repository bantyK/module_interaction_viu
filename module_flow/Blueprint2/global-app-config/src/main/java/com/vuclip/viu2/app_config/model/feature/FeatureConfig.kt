package com.vuclip.viu2.app_config.model.feature


import com.google.gson.annotations.SerializedName

data class FeatureConfig(
    val blackbox: Boolean,
    val debug: Boolean,
    @SerializedName("NOTE")
    val nOTE: String
)