package com.vuclip.viu2.app_config.model.feature


import com.google.gson.annotations.SerializedName

data class Feature(
    @SerializedName("feature_components")
    val featureComponents: List<FeatureComponent>,
    @SerializedName("feature_config")
    val featureConfig: FeatureConfig,
    @SerializedName("feature_id")
    val featureId: String,
    @SerializedName("feature_info")
    val featureInfo: FeatureInfo,
    @SerializedName("feature_props")
    val featureProps: FeatureProps,
    @SerializedName("feature_state_machine")
    val featureStateMachine: FeatureStateMachine,
    @SerializedName("platform_config")
    val platformConfig: PlatformConfig
) {
    override fun toString(): String {
        return featureId
    }
}