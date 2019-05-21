package com.vuclip.viu2.programming

import android.util.Log
import com.vuclip.viu2.app_config.model.feature.FeatureComponent
import com.vuclip.viu2.base.BaseComponent

class ProgrammingComponent : BaseComponent {
    override fun invoke(component: FeatureComponent) {
        Log.d("Viu", "Programming component invoke")
    }
}