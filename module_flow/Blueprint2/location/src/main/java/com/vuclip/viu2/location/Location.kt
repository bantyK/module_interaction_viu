package com.vuclip.viu2.location

import android.util.Log
import com.vuclip.viu2.app_config.model.feature.FeatureComponent
import com.vuclip.viu2.base.Invokable
import com.vuclip.viu2.base.SignalDispatcher

class Location : Invokable {

    override fun invoke(component: FeatureComponent, signalDispatcher: SignalDispatcher) {
        Log.d("Viu", "${Thread.currentThread().name} -> Location component invoke")
        Thread.sleep(2_000)
        signalDispatcher.onSignalReceived(component.componentStateMachine.end, component)
    }
}