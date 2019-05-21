package com.vuclip.viu2.app_config.model.feature


import com.google.gson.annotations.SerializedName

data class FeatureStateMachine(
    @SerializedName("end_signal")
    val endSignal: String,
    @SerializedName("launch_component")
    val launchComponent: String,
    @SerializedName("NOTE")
    val nOTE: String,
    @SerializedName("start_signal")
    val startSignal: String
)