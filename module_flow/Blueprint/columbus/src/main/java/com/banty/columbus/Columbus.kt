package com.banty.columbus

import com.banty.core.signal.Signal

/**
 * Created by Banty on 2019-05-02.
 */
class Columbus {

    private var router: Router? = null

    companion object {
        private val INSTANCE = Columbus()

        fun getColumbus(): Columbus {
            return INSTANCE
        }
    }

    fun registerRouter(router: Router) {
        this.router = router
    }


    fun unregisterRouter() {
        this.router = null
    }

    fun postEvent(signal: Signal) {
        if (router == null)
            throw IllegalStateException("No router is registered to Columbus")

        when(signal) {
            Signal.APP_INIT_START -> {
                router!!.navigateTo(Signal.APP_INIT_START)
            }

            Signal.SHOW_HOME -> {
                router!!.navigateTo(Signal.SHOW_HOME)
            }
        }

    }
}