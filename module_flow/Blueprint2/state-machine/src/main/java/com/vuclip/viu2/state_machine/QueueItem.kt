package com.vuclip.viu2.state_machine

import com.vuclip.viu2.app_config.model.feature.FeatureComponent

data class QueueItem(
    val signal: String,
    val component: FeatureComponent,
    val pendingSignals: ArrayList<String>
) {
    override fun toString(): String {
        return component.componentId
    }
}
