package com.banty.flowcommwithmodule

import android.app.Application
import com.banty.columbus.Columbus

/**
 * Created by Banty on 2019-04-30.
 */
class ViuApp : Application() {

    override fun onCreate() {
        super.onCreate()

        com.banty.columbus.Columbus.getInstance().initColumbus()
    }
}