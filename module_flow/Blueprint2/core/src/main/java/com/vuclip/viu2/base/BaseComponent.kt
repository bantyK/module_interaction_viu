package com.vuclip.viu2.base

import com.vuclip.viu2.app_config.model.feature.FeatureComponent

interface BaseComponent {

    fun invoke(component: FeatureComponent)
}