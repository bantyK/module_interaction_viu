package com.vuclip.viu2.state_machine

import android.util.Log
import com.vuclip.viu2.app_config.model.feature.FeatureComponent

class SignalResolver(
    private val dependencyResolver: com.vuclip.viu2.state_machine.DependencyResolver
) {
    private val signalList = ArrayList<QueueItem>()

    fun add(queueItem: QueueItem) {
        signalList.add(0, queueItem)
        Log.d("AppInitStateMachine", "Added to queue\n\tQueue updated: $signalList\n\t")
    }

    fun getInvokable(): FeatureComponent? {
        for (i in 0 until signalList.size) {
            // check if all pending signals are already resolved
            if (dependencyResolver.checkAllDependencyResolved(signalList[i])) {
                Log.d("AppInitStateMachine", "Removed ${signalList[i]} from queue, Queue:\n\t$signalList")
                return signalList.removeAt(i).component
            }
        }
        return null
    }

}