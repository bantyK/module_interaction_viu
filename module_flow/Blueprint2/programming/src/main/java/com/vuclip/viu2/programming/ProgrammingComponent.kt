package com.vuclip.viu2.programming

import android.util.Log
import com.vuclip.viu2.app_config.model.feature.FeatureComponent
import com.vuclip.viu2.base.Invokable
import com.vuclip.viu2.base.SignalDispatcher

class ProgrammingComponent : Invokable {
    override fun invoke(component: FeatureComponent, signalDispatcher: SignalDispatcher) {
        Log.d("Viu", "Programming component invoke")
        signalDispatcher.onSignalReceived(component.componentStateMachine.end, component)
    }
}