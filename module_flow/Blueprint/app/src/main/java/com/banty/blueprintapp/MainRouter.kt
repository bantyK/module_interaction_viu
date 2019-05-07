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
class MainRouter(
        private val view: MainActivityView,
        private val columbus: Columbus,
        private val objectMapper: ObjectMapper) : Router {

    /**
     * List of all the active flows
     * */
    private val activeFlows = arrayListOf<Flow>()

    /**
     * Signal - Flow mapping.
     * These flows are in paused state and are waiting for the event to occur which is identified by the key
     * */
    private val waitingFlows = HashMap<Signal, ArrayList<Flow>>()

    fun startInit(fragmentContainer: Int) {
        val payload = HashMap<String, Any>()
        payload["fragment_container"] = fragmentContainer
        columbus.postEvent(Signal.APP_INIT_START, payload)
    }

    override fun navigateTo(signal: Signal, payload: HashMap<String, Any>, awaitSignal: Signal?) {
        val flowList = waitingFlows[signal]
        if (flowList != null  && anyFlowWaitingForSignal(signal, flowList)) {
            // there are flows which is waiting for this event to occur.
            for (flow in flowList) {
                flow.await(signal)
                flowList.remove(flow)
            }
        } else {
            val flow = objectMapper.getClassName(signal).newInstance() as Flow
            Log.i("Viu", "Fragment name : ${flow.javaClass.simpleName}")
            addWaitingFlow(flow, signal, awaitSignal)
            view.navigateTo(flow, payload)
        }
    }

    private fun anyFlowWaitingForSignal(signal: Signal, flowList: ArrayList<Flow>?) =
            flowList != null && flowList.isNotEmpty() && waitingFlows.containsKey(signal)

    private fun addWaitingFlow(flow: Flow, signal: Signal, awaitSignal: Signal?) {
        if (awaitSignal != null) {
            if (waitingFlows[awaitSignal] == null) {
                waitingFlows[awaitSignal] = ArrayList()
            }
            waitingFlows[awaitSignal]?.add(flow)
        }
    }

    fun register() {
        columbus.registerRouter(this)
    }

    fun unregister() {
        columbus.unregisterRouter()
    }

}
