package com.vuclip.viu2.base

import com.vuclip.viu2.app_config.model.feature.FeatureComponent

interface SignalDispatcher {

    fun onSignalReceived(signal:String, component: FeatureComponent)
}