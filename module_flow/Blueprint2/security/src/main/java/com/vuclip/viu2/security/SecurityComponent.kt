package com.vuclip.viu2.security

import android.util.Log
import com.vuclip.viu2.app_config.model.feature.FeatureComponent
import com.vuclip.viu2.base.Invokable
import com.vuclip.viu2.base.SignalDispatcher

class SecurityComponent : Invokable {
    val TAG = "SecurityComponent"

    override fun invoke(component: FeatureComponent, signalDispatcher: SignalDispatcher) {
        Log.d(TAG, "Security component init")
        signalDispatcher.onSignalReceived(component.componentStateMachine.end, component)
    }
}