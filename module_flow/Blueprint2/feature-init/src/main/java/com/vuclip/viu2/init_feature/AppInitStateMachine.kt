package com.vuclip.viu2.init_feature

import android.util.Log
import com.vuclip.viu2.app_config.model.feature.FeatureComponent
import com.vuclip.viu2.base.Invokable
import com.vuclip.viu2.base.SignalDispatcher
import com.vuclip.viu2.base.StateMachine

class AppInitStateMachine : StateMachine, SignalDispatcher {

    private val dependencyResolver = DependencyResolver()
    private val signalResolver = SignalResolver(dependencyResolver)

    val mapper = SignalModuleMapper()

    override fun onSignalReceived(signal: String, component: FeatureComponent) {
        processSignal(signal, component)
    }

    override fun startEngine(components: List<FeatureComponent>) {
        Log.d("AppInitStateMachine", "State machine started with ${components.size} components")
        for (component in components) {
            processSignal(component.componentStateMachine.start, component)
        }
    }

    override fun processSignal(
        signal: String,
        component: FeatureComponent
    ) {
        Log.d("AppInitStateMachine", "Signal received: $signal")

        // if its an end-signal .. add to "Completed list"
        // else add to "Pending"
        if (signal == component.componentStateMachine.end) {
            dependencyResolver.addCompletedComponent(component.componentId)
        } else {
            val pendingSignals = dependencyResolver.getPendingSignals(component.componentProps.dependencies)
            signalResolver.add(QueueItem(signal, component, pendingSignals))
            Log.d("AppInitStateMachine", "Dependencies for $signal:\n\t $pendingSignals")
        }

        // Execution logic
        // Pop from queue, check for dependencies, execute!

        // we need to check if currently active feature-flow allows newly received signal
        // if yes -- go for execution

        // if no -- use standard process of finding next eligible signal

        var componentToExecute: FeatureComponent? = signalResolver.getInvokable()

        if (componentToExecute == null) {
            Log.d("AppInitStateMachine", "Nothing ready to execute")
        } else {
            executeSignal(componentToExecute)
        }

    }


    private fun executeSignal(component: FeatureComponent) {
        Log.d("AppInitStateMachine", "Invoking : ${component.componentId}")
        (mapper.getModule(component.componentId) as Invokable).invoke(component, this)
    }

}