package com.banty.blueprintapp

import com.banty.columbus.Columbus
import com.banty.columbus.Router
import com.banty.core.identity.ViuUser
import com.banty.core.model.Clip
import com.banty.core.signal.Signal
import com.banty.home.HomeFragment
import com.banty.init.app_init.SplashFragment
import com.banty.player.PlayerFragment
import java.lang.IllegalArgumentException
import java.util.*

/**
 * Created by Banty on 2019-05-02.
 */
class MainPresenter(private val view: MainActivityView, private val columbus: Columbus) : Router {

    fun startInit() {
        columbus.postEvent(Signal.APP_INIT_START)
    }

    override fun navigateTo(signal: Signal, payload: HashMap<String, Any>) {
        when (signal) {
            Signal.APP_INIT_START -> {
                view.navigateTo(SplashFragment(), payload)
            }

            Signal.SHOW_HOME -> {
                view.navigateTo(HomeFragment(), payload)
            }

            Signal.SHOW_PLAYER -> {
                if(payload.containsKey("clip")){
                    val clip = payload["clip"] as Clip
                    if(clip.isPaid) {
                        // open subscription here
                    } else {
                        view.navigateTo(PlayerFragment(), payload)
                    }
                } else {
                    throw IllegalArgumentException("No clip passed")
                }
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