package com.vuclip.viu2.init_feature

import com.vuclip.viu2.app_config.model.feature.Feature
import com.vuclip.viu2.app_config.model.feature.FeatureComponent
import com.vuclip.viu2.base.FeatureLauncher
import com.vuclip.viu2.base.FeatureRouter
import com.vuclip.viu2.base.Invokable
import com.vuclip.viu2.base.SignalDispatcher
import com.vuclip.viu2.state_machine.StateMachineImpl
import com.vuclip.viu2.state_machine.util.SupportedSignalUtility

// feature launcher
class AppInitLauncher : FeatureRouter, FeatureLauncher {

    private val appInitStateMachineImpl = StateMachineImpl()
    private val supportedSignalUtility = SupportedSignalUtility()

    override fun route(componentId: String, component: FeatureComponent, signalDispatcher: SignalDispatcher) {
        (SignalModuleMapper().getModule(componentId) as Invokable).invoke(component, signalDispatcher)
    }

    override fun init(feature: Feature) {
        appInitStateMachineImpl.registerRouter(this)

        supportedSignalUtility.storeSupportedSignals(feature)

        appInitStateMachineImpl.startEngine(feature)
    }
}