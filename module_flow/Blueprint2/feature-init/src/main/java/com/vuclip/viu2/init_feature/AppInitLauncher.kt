package com.vuclip.viu2.init_feature

import android.util.Log
import com.vuclip.viu2.app_config.model.feature.Feature
import com.vuclip.viu2.app_config.model.feature.FeatureComponent
import com.vuclip.viu2.base.FeatureLauncher
import com.vuclip.viu2.base.FeatureRouter
import com.vuclip.viu2.base.Invokable
import com.vuclip.viu2.base.SignalDispatcher
import com.vuclip.viu2.state_machine.StateMachineImpl

// feature launcher
class AppInitLauncher : FeatureRouter, FeatureLauncher {

    private val appInitStateMachineImpl = StateMachineImpl()

    // list of signals supported by this feature.
    // If a signal is received which is not present in supported signal list, then it will be sent to Columbus


    override fun route(componentId: String, component: FeatureComponent, signalDispatcher: SignalDispatcher) {
        (SignalModuleMapper().getModule(componentId) as Invokable).invoke(component, signalDispatcher)
    }

    override fun init(feature: Feature) {
        Log.d("AppInit", "App init")

        appInitStateMachineImpl.registerRouter(this)

        appInitStateMachineImpl.setSupportedDependencies(feature)

        appInitStateMachineImpl.startEngine(feature)
    }
}