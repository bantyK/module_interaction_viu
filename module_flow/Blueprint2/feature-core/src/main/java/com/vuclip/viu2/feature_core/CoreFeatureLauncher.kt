package com.vuclip.viu2.feature_core

import android.util.Log
import com.vuclip.viu2.app_config.model.feature.Feature
import com.vuclip.viu2.base.FeatureLauncher

class CoreFeatureLauncher : FeatureLauncher {
    override fun init(feature: Feature) {
        Log.d("Viu", "Core module init")
    }

}