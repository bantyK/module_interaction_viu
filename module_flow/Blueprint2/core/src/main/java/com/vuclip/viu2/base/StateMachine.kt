package com.vuclip.viu2.base

import com.vuclip.viu2.app_config.model.feature.FeatureComponent

interface StateMachine {

    fun startEngine(components: List<FeatureComponent>)

    fun processSignal(signal: String, component: FeatureComponent) // should be a separate class for Signal
}