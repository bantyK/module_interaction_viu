package com.vuclip.viu2.app_config.model.feature


import com.google.gson.annotations.SerializedName

data class ComponentProps(
    val dependencies: List<String>,
    @SerializedName("has_ui")
    val hasUi: Boolean,
    @SerializedName("needs_permissions")
    val needsPermissions: String,
    @SerializedName("needs_ui")
    val needsUi: Boolean,
    @SerializedName("runs_in_background")
    val runsInBackground: Boolean
)