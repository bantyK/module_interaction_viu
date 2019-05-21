package com.vuclip.viu2.init_feature

import com.vuclip.viu2.app_config.model.feature.Feature
import com.vuclip.viu2.base.StateMachine

// feature launcher
class AppInitLauncher {

    fun init(featureConfig: Feature) {
        val featureComponents = featureConfig.featureComponents
        AppInitStateMachine().startEngine(featureComponents)
    }

}