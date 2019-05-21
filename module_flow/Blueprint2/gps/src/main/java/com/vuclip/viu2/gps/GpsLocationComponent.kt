package com.vuclip.viu2.gps

import android.util.Log
import com.vuclip.viu2.app_config.model.feature.FeatureComponent
import com.vuclip.viu2.base.BaseComponent

class GpsLocationComponent : BaseComponent {
    override fun invoke(component: FeatureComponent) {
        Log.d("Viu", "${Thread.currentThread().name} -> Gps location component invoke")
    }


}