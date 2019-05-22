package com.vuclip.viu2.blueprint2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vuclip.viu2.app_config.AppConfigLoader
import com.vuclip.viu2.app_config.model.feature.Feature
import com.vuclip.viu2.base.FeatureLauncher

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startLauncherComponent()
    }

    private fun startLauncherComponent() {

//        initColumbus()

        startCoreFeature()

        startLauncherFeature()
    }

    private fun startCoreFeature() {
        launchModule(AppConfigLoader.getInstance().getCoreFeature())
    }

    private fun startLauncherFeature() {
        val launcherFeatureId = AppConfigLoader.getInstance().getLauncher()
        if (launcherFeatureId != null) {
            val featureConfig = AppConfigLoader.getInstance().getFeatureConfig(launcherFeatureId)
            launchModule(featureConfig)
        }
    }

    private fun launchModule(feature: Feature?) {
        if (feature != null) {
            val featureLauncherClass = feature.platformConfig.android.launcherModule
            (Class.forName(featureLauncherClass).newInstance() as FeatureLauncher).init(feature)
        } else {
            throw FeatureNotFoundException("${feature} not found")
        }
    }
}

class FeatureNotFoundException(s: String) : Throwable()
