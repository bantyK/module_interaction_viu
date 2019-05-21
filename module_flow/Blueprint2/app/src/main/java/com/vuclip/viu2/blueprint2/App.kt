package com.vuclip.viu2.blueprint2

import android.app.Application
import com.vuclip.viu2.app_config.AppConfigLoader

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        AppConfigLoader.getInstance().init(this)
    }
}