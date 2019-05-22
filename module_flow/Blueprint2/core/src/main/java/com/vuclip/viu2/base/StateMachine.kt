package com.vuclip.viu2.base

import com.vuclip.viu2.app_config.model.feature.Feature
import com.vuclip.viu2.app_config.model.feature.FeatureComponent

interface StateMachine {

    fun startEngine(feature: Feature)

    fun registerRouter(router: FeatureRouter)

    fun processSignal(signal: String, component: FeatureComponent) // should be a separate class for Signal
}