package com.vuclip.viu2.base

import com.vuclip.viu2.app_config.model.feature.Feature

interface FeatureLauncher {

    fun init(feature: Feature)
}