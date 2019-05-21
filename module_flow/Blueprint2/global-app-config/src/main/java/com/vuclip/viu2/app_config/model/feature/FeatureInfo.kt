package com.vuclip.viu2.app_config.model.feature


import com.google.gson.annotations.SerializedName

data class FeatureInfo(
    val author: String,
    val changelog: String,
    val desc: String,
    @SerializedName("NOTE")
    val nOTE: String,
    val name: String,
    val updated: String,
    val version: String
)