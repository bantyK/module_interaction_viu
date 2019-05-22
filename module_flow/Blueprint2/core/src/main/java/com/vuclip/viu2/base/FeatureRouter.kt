package com.vuclip.viu2.base

import com.vuclip.viu2.app_config.model.feature.FeatureComponent

interface FeatureRouter {
    fun route(componentId: String, component: FeatureComponent, signalDispatcher: SignalDispatcher)
}