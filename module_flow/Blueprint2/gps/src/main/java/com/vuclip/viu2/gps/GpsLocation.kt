package com.vuclip.viu2.gps

import android.util.Log
import com.vuclip.viu2.app_config.model.feature.FeatureComponent
import com.vuclip.viu2.base.Invokable
import com.vuclip.viu2.base.SignalDispatcher

class GpsLocation : Invokable {
    override fun invoke(component: FeatureComponent, signalDispatcher: SignalDispatcher) {
        Log.d("Viu", "${Thread.currentThread().name} -> Gps location component invoke")
        Thread.sleep(2000)
        signalDispatcher.onSignalReceived(component.componentStateMachine.end, component)
    }


}