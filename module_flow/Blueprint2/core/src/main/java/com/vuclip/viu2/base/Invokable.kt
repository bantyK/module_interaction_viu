package com.vuclip.viu2.base

import com.vuclip.viu2.app_config.model.feature.FeatureComponent

interface Invokable {

    fun invoke(component: FeatureComponent, signalDispatcher: SignalDispatcher)
}