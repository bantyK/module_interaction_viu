package com.banty.blueprintapp

import android.util.Log
import com.banty.blueprintapp.mapper.ObjectMapper
import com.banty.columbus.Columbus
import com.banty.columbus.Router
import com.banty.core.Flow
import com.banty.core.signal.Signal

/**
 * Created by Banty on 2019-05-02.
 */
class MainPresenter(
        private val view: MainActivityView,
        private val columbus: Columbus,
        private val objectMapper: ObjectMapper) : Router {

    fun startInit(fragment_container: Int) {
        val payload = HashMap<String, Any>()

        payload["fragment_container"] = fragment_container

        columbus.postEvent(Signal.APP_INIT_START, payload)

    }

    override fun navigateTo(signal: Signal, payload: HashMap<String, Any>) {
        val module = objectMapper.getClassName(signal).newInstance() as Flow
        Log.i("Viu", "Fragment name : ${module.javaClass.simpleName}")
        view.navigateTo(module, payload)
    }

    fun register() {
        columbus.registerRouter(this)
    }

    fun unregister() {
        columbus.unregisterRouter()
    }

}