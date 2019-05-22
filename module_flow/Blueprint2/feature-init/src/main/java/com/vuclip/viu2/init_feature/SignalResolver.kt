package com.vuclip.viu2.init_feature

import android.util.Log
import com.vuclip.viu2.app_config.model.feature.FeatureComponent

class SignalResolver(
    private val dependencyResolver: DependencyResolver
) {
    private val signalList = ArrayList<QueueItem>()

    fun add(queueItem: QueueItem) {
        signalList.add(0, queueItem)
    }

    fun getInvokable(): FeatureComponent? {
        var componentToExecute: FeatureComponent? = null
        for (i in 0 until signalList.size) {
            // check if all pending signals are already resolved
            if (dependencyResolver.checkAllDependencyResolved(signalList[i])) {
                return signalList.removeAt(i).component
            }
        }
        Log.d("AppInitStateMachine", "Added to queue\n\tQueue updated: $signalList\n\t")
        return componentToExecute
    }

}