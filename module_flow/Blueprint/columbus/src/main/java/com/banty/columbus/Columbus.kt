package com.banty.columbus

import com.banty.core.signal.Signal


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

    /**
     * All the flows will use this method for posting events to Columbus
     *
     * @param signal is the name of the signal that the flow want to post to Columbus.
     *
     * @param payload is the data that the flows would want to pass to other flows or Columbus
     *
     * @param awaitSignal is an optional signal. If a flow pass a non-null value for this param, this will mean that the flow is still active
     * but waiting for some other event to proceed further. On getting this value, Columbus will keep this flow in waiting state and resume its
     * operation when it receives the awaitSignal.
     * */
    fun postEvent(signal: Signal, payload: HashMap<String, Any>, awaitSignal:Signal? = null) {
        if (router == null)
            throw IllegalStateException("No router is registered to Columbus")

        router!!.navigateTo(signal, payload, awaitSignal)

    }
}