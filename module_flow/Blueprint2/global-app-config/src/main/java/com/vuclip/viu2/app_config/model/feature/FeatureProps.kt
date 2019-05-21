package com.vuclip.viu2.app_config.model.feature


import com.google.gson.annotations.SerializedName

data class FeatureProps(
    val dependencies: List<String>,
    val fullscreen: Boolean,
    @SerializedName("has_ui")
    val hasUi: Boolean,
    @SerializedName("NOTE")
    val nOTE: String,
    @SerializedName("needs_permissions")
    val needsPermissions: String,
    @SerializedName("needs_ui")
    val needsUi: Boolean,
    @SerializedName("runs_in_background")
    val runsInBackground: Boolean,
    @SerializedName("toast_allowed")
    val toastAllowed: Boolean
)