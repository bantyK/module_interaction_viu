package com.vuclip.viu2.init_feature

import android.util.Log
import com.vuclip.viu2.app_config.model.feature.FeatureComponent
import com.vuclip.viu2.base.BaseComponent
import com.vuclip.viu2.base.StateMachine
import java.util.*

class AppInitStateMachine : StateMachine {
    override fun startEngine(components: List<FeatureComponent>) {
        Log.d("AppInitStateMachine", "State machine started with ${components.size} components")
        for (component in components) {
            processSignal(component.componentStateMachine.start, component)
        }
    }


    private val completedComponents = arrayListOf<String>()

    private val signalQueue = ArrayDeque<QueueItem>()

    val mapper = SignalModuleMapper()

    override fun processSignal(
        signal: String,
        component: FeatureComponent
    ) {
        Log.d("AppInitStateMachine", "Signal received: $signal")

        // adding to ready list
        if (signal == component.componentStateMachine.end) {
            completedComponents.add(component.componentId)
            Log.d("AppInitStateMachine", "ready signals:\n\t $completedComponents")
        }

        // find out pending signals
        val pendingSignals = getPendingSignals(component)
        Log.d("AppInitStateMachine", "Unresolved dependency for ${signal}:\n\t $pendingSignals")

        if (pendingSignals.isNotEmpty()) {
            signalQueue.offer(QueueItem(signal, component, pendingSignals))
        } else {
            // process the signal
            executeSignal(component)
        }


        // 1. QUEUE UP
        // Signal, Component

        // 2. PROCESS
        // check if there is a dependency,
//        if(pendingDependecies(component))
        //update the queue -- // Signal, Component, Waiting-Signal
        // if yes -- put on hold
        // else -- invoke

    }

    private fun executeSignal(component: FeatureComponent) {
        Log.d("AppInitStateMachine", "Invoking : ${component.componentId}")
        (mapper.getModule(component.componentId) as BaseComponent).invoke(component)
    }

    private fun getPendingSignals(component: FeatureComponent): ArrayList<String> {
        // enlist the dependent signals
        // check if signal is ready
        val dependencies = component.componentProps.dependencies
        val unResolvedDependencies = arrayListOf<String>()
        for (dependency in dependencies) {
            if (!completedComponents.contains(dependency)) {
                unResolvedDependencies.add(dependency)
            }
        }
        return unResolvedDependencies
    }
}