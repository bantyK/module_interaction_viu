package com.vuclip.viu2.device

import android.os.Build
import android.util.Log
import com.vuclip.viu2.app_config.model.feature.FeatureComponent
import com.vuclip.viu2.base.BaseComponent

class DeviceComponent : BaseComponent {

    override fun invoke(component: FeatureComponent) {
        Log.d("Viu", "${Thread.currentThread().name} -> Device component invoke")
        Log.d("Viu", component.componentStateMachine.end)
    }

    fun getDeviceInfo(): String {
        return Build.MANUFACTURER
    }
}