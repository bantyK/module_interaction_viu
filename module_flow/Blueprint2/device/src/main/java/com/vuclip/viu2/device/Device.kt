package com.vuclip.viu2.device

import android.os.Build
import android.util.Log
import com.vuclip.viu2.app_config.model.feature.FeatureComponent
import com.vuclip.viu2.base.Invokable
import com.vuclip.viu2.base.SignalDispatcher

class Device : Invokable {

    override fun invoke(component: FeatureComponent, signalDispatcher: SignalDispatcher) {
        Log.d("Viu", "${Thread.currentThread().name} -> Device component invoke")

        signalDispatcher.onSignalReceived(component.componentStateMachine.end, component)
    }

    fun getDeviceInfo(): String {
        return Build.MANUFACTURER
    }
}