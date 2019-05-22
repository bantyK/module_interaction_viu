package com.vuclip.viu2.init_feature

import com.vuclip.viu2.app_config.model.feature.Feature
import com.vuclip.viu2.app_config.model.feature.FeatureComponent
import com.vuclip.viu2.base.FeatureRouter
import com.vuclip.viu2.base.Invokable
import com.vuclip.viu2.base.SignalDispatcher
import com.vuclip.viu2.state_machine.StateMachineImpl

// feature launcher
class AppInitLauncher : FeatureRouter {

    private val appInitStateMachineImpl = StateMachineImpl()

    override fun route(componentId: String, component: FeatureComponent, signalDispatcher: SignalDispatcher) {
        (SignalModuleMapper().getModule(componentId) as Invokable).invoke(component, signalDispatcher)
    }

    fun init(features: Feature) {
        appInitStateMachineImpl.registerRouter(this)
        appInitStateMachineImpl.startEngine(features)
    }

}