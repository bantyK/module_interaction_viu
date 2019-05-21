package com.vuclip.viu2.location

import android.util.Log
import com.vuclip.viu2.app_config.model.feature.FeatureComponent
import com.vuclip.viu2.base.BaseComponent

class LocationComponent : BaseComponent {

    override fun invoke(component: FeatureComponent) {
        Log.d("Viu", "${Thread.currentThread().name} -> Location component invoke")
    }
}