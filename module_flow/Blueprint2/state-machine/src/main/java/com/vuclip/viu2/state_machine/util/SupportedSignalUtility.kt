package com.vuclip.viu2.state_machine.util

import com.vuclip.viu2.app_config.model.feature.Feature

class SupportedSignalUtility {

    // list of signals supported by this feature.
    // If a signal is received which is not present in supported signal list, then it will be sent to Columbus
    private val supportedSignals = arrayListOf<String>()

    fun storeSupportedSignals(features: Feature) {
        for (component in features.featureComponents) {
            supportedSignals.add(component.componentStateMachine.start)
            supportedSignals.add(component.componentStateMachine.end)
        }
    }
}