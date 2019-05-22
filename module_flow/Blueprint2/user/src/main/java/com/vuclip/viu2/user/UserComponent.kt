package com.vuclip.viu2.user

import android.util.Log
import com.vuclip.viu2.app_config.model.feature.FeatureComponent
import com.vuclip.viu2.base.Invokable
import com.vuclip.viu2.base.SignalDispatcher

class UserComponent : Invokable {
    override fun invoke(component: FeatureComponent, signalDispatcher: SignalDispatcher) {
        Log.d("Viu", "User component invoke")
        signalDispatcher.onSignalReceived(component.componentStateMachine.end, component)
    }
}