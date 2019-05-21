package com.vuclip.viu2.network

import android.util.Log
import com.vuclip.viu2.app_config.model.feature.FeatureComponent
import com.vuclip.viu2.base.Invokable
import com.vuclip.viu2.base.SignalDispatcher

class NetworkComponent : Invokable {
    override fun invoke(component: FeatureComponent, signalDispatcher: SignalDispatcher) {
        Log.d("Network", "Network component invoked")
        Thread.sleep(2_000)
        signalDispatcher.onSignalReceived(component.componentStateMachine.end, component)
    }
}