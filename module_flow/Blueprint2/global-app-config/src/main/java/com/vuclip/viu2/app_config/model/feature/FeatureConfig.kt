package com.vuclip.viu2.app_config.model.feature


import com.google.gson.annotations.SerializedName

data class FeatureConfig(
    @SerializedName("on_demand_components")
    val onDemandComponents : List<String>
)