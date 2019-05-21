package com.vuclip.viu2.init_feature

import android.util.Log

class DependencyResolver {
    private val completedComponents = arrayListOf<String>()

    fun addCompletedComponent(componentId: String) {
        completedComponents.add(componentId)
        Log.d("AppInitStateMachine", "completed signals:\n\t $completedComponents")
    }


    fun checkAllDependencyResolved(queueItem: QueueItem): Boolean {
        for (signal in queueItem.pendingSignals) {
            if (!completedComponents.contains(signal)) {
                return false
            }
        }
        return true
    }

    fun getPendingSignals(dependencies: List<String>): ArrayList<String> {
        // enlist the dependent signals
        // check if signal is ready
        val unResolvedDependencies = arrayListOf<String>()
        for (dependency in dependencies) {
            if (!completedComponents.contains(dependency)) {
                unResolvedDependencies.add(dependency)
            }
        }
        return unResolvedDependencies
    }
}