package com.banty.base

import com.banty.base.signals.Signal
import org.greenrobot.eventbus.EventBus

/**
 * Created by Banty on 2019-04-30.
 */
class ViuEventBus {

    private val eventBus: EventBus = EventBus.getDefault()

    private var subscribed = false

    companion object {
        private val INSTANCE = ViuEventBus()

        fun getInstance(): ViuEventBus = INSTANCE
    }

    fun postEvent(signal: Signal) {
        if (!subscribed) {
            throw IllegalStateException("No subscriber available")
        } else {
            eventBus.post(signal)
        }
    }

    fun subscribe(subscriber: Any) {
        eventBus.register(subscriber)
        subscribed = true
    }


}