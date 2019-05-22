package com.vuclip.viu2.app_config.model.feature


import com.google.gson.annotations.SerializedName

data class FeatureComponent(
    @SerializedName("component_config")
    val componentConfig: ComponentConfig,
    @SerializedName("component_id")
    val componentId: String,
    @SerializedName("component_info")
    val componentInfo: ComponentInfo,
    @SerializedName("component_props")
    val componentProps: ComponentProps?,
    @SerializedName("component_state_machine")
    val componentStateMachine: ComponentStateMachine
) {
    override fun toString(): String {
        return componentId
    }
}