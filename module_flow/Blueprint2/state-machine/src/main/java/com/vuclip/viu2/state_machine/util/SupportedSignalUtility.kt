package com.vuclip.viu2.state_machine.util

import com.vuclip.viu2.app_config.model.feature.Feature

object SupportedSignalUtility {

    fun storeSupportedComponents(supportedSignals: ArrayList<String>, features: Feature) {
        for (component in features.featureComponents) {
            supportedSignals.add(component.componentId)
        }
    }

}