package com.vuclip.viu2.state_machine

import android.util.Log
import com.vuclip.viu2.app_config.model.feature.ComponentProps

class DependencyResolver {
    private val completedComponents = arrayListOf<String>()

    fun addCompletedComponent(componentId: String) {
        completedComponents.add(componentId)
        Log.d("AppInitStateMachine", "completed signals:\n\t $completedComponents, size: ${completedComponents.size}")
    }


    fun checkAllDependencyResolved(queueItem: QueueItem): Boolean {
        for (signal in queueItem.pendingSignals) {
            if (!completedComponents.contains(signal)) {
                return false
            }
        }
        return true
    }

    fun getPendingSignals(componentProps: ComponentProps?): ArrayList<String> {
        // enlist the dependent signals
        // check if signal is ready

        val unResolvedDependencies = arrayListOf<String>()
        if (componentProps != null) {
            val dependencies = componentProps.dependencies
            if (dependencies != null) {
                for (dependency in dependencies) {
                    if (!completedComponents.contains(dependency)) {
                        unResolvedDependencies.add(dependency)
                    }
                }
            }
        }

        return unResolvedDependencies
    }
}