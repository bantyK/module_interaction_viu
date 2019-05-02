package com.banty.columbus

import com.banty.core.signal.Signal
import com.banty.init.app_init.AppInitSignal

/**
 * Created by Banty on 2019-05-02.
 */
class Columbus {

    companion object {
        private val INSTANCE = Columbus()

        fun getColumbus(): Columbus {
            return INSTANCE
        }
    }

    fun postEvent(signal: Signal) {
        when(signal) {
            AppInitSignal.APP_INIT_START -> {

            }
        }
    }
}