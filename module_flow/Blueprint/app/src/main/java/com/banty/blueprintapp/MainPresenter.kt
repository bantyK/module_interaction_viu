package com.banty.blueprintapp

import com.banty.columbus.Columbus
import com.banty.columbus.Router
import com.banty.core.signal.Signal
import com.banty.home.HomeFragment
import com.banty.init.app_init.SplashFragment

/**
 * Created by Banty on 2019-05-02.
 */
class MainPresenter(private val view: MainActivityView, private val columbus: Columbus) : Router {

    fun startInit() {
        columbus.postEvent(Signal.APP_INIT_START)
    }

    override fun navigateTo(signal: Signal) {
        when (signal) {
            Signal.APP_INIT_START -> {
                view.navigateTo(SplashFragment())
            }

            Signal.SHOW_HOME -> {
                view.navigateTo(HomeFragment())
            }
        }
    }

    fun register() {
        columbus.registerRouter(this)
    }

    fun unregister() {
        columbus.unregisterRouter()
    }

}