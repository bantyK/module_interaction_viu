package com.vuclip.viu2.user

import android.util.Log
import com.vuclip.viu2.app_config.model.feature.FeatureComponent
import com.vuclip.viu2.base.BaseComponent

class UserComponent : BaseComponent {
    override fun invoke(component: FeatureComponent) {
        Log.d("Viu", "User component invoke")
    }
}