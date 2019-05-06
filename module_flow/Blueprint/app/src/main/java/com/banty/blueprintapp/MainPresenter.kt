package com.banty.blueprintapp

import android.util.Log
import androidx.fragment.app.Fragment
import com.banty.blueprintapp.mapper.ObjectMapper
import com.banty.columbus.Columbus
import com.banty.columbus.Router
import com.banty.core.signal.Signal

/**
 * Created by Banty on 2019-05-02.
 */
class MainPresenter(
        private val view: MainActivityView,
        private val columbus: Columbus,
        private val objectMapper: ObjectMapper) : Router {

    fun startInit() {
        columbus.postEvent(Signal.APP_INIT_START)

    }

    override fun navigateTo(signal: Signal, payload: HashMap<String, Any>) {
        val fragment = objectMapper.getClassName(signal).newInstance() as Fragment
        Log.i("Viu", "Fragment name : ${fragment.javaClass.simpleName}")
        view.navigateTo(fragment)
    }

    fun register() {
        columbus.registerRouter(this)
    }

    fun unregister() {
        columbus.unregisterRouter()
    }

}