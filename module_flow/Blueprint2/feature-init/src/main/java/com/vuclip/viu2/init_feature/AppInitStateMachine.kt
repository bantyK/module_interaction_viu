package com.vuclip.viu2.init_feature

import android.util.Log
import com.vuclip.viu2.app_config.model.feature.FeatureComponent
import com.vuclip.viu2.base.Invokable
import com.vuclip.viu2.base.SignalDispatcher
import com.vuclip.viu2.base.StateMachine
import java.util.concurrent.Executors

class AppInitStateMachine : StateMachine, SignalDispatcher {

    private val dependencyResolver = DependencyResolver()
    private val signalResolver = SignalResolver(dependencyResolver)

    private var componentToExecute: FeatureComponent? = null

    private val mapper = SignalModuleMapper()

    override fun onSignalReceived(signal: String, component: FeatureComponent) {
        processSignal(signal, component)
    }

    override fun startEngine(components: List<FeatureComponent>) {
        Log.d("AppInitStateMachine", "State machine started with:\n\t ${components}, size: ${components.size}")
        for (component in components) {
            processSignal(component.componentStateMachine.start, component)
        }
    }

    override fun processSignal(
        signal: String,
        component: FeatureComponent
    ) {
        Log.d("AppInitStateMachine", "Signal received: $signal")

        if (signal == component.componentStateMachine.end) {
            dependencyResolver.addCompletedComponent(component.componentId)
        } else {
            val pendingSignals = dependencyResolver.getPendingSignals(component.componentProps)
            signalResolver.add(QueueItem(signal, component, pendingSignals))
            Log.d("AppInitStateMachine", "Dependencies for $signal:\n\t $pendingSignals")
        }

        componentToExecute = signalResolver.getInvokable()

        if (componentToExecute == null) {
            Log.d("AppInitStateMachine", "Nothing ready to execute")
        } else {
            executeSignal(componentToExecute)
        }
    }


    private fun executeSignal(component: FeatureComponent?) {
        if (component != null) {
            Log.d("AppInitStateMachine", "Invoking : ${component.componentId}")
            Executors.newCachedThreadPool().submit {
                (mapper.getModule(component.componentId) as Invokable).invoke(component, this)
            }
        }
    }

}