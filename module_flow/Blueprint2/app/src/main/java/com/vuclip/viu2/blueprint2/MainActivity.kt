package com.vuclip.viu2.blueprint2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vuclip.viu2.app_config.AppConfigLoader
import com.vuclip.viu2.init_feature.AppInitLauncher

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startLauncherComponent()
    }

    private fun startLauncherComponent() {
        val launcherFeatureId = AppConfigLoader.getInstance().getLauncher()
        if (launcherFeatureId != null) {
            val featureConfig = AppConfigLoader.getInstance().getFeatureConfig(launcherFeatureId)
            if (featureConfig != null) {
                val launcherModule = featureConfig.platformConfig.android.launcherModule
                (Class.forName(launcherModule).newInstance() as AppInitLauncher).init(featureConfig)
            }
        }
    }
}
