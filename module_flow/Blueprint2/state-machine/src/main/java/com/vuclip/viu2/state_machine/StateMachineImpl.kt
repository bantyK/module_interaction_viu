package com.vuclip.viu2.state_machine

import android.util.Log
import com.vuclip.viu2.app_config.model.feature.Feature
import com.vuclip.viu2.app_config.model.feature.FeatureComponent
import com.vuclip.viu2.base.FeatureRouter
import com.vuclip.viu2.base.SignalDispatcher
import com.vuclip.viu2.base.StateMachine
import java.util.concurrent.Executors

class StateMachineImpl : StateMachine, SignalDispatcher {
    private val dependencyResolver = DependencyResolver()

    private val signalResolver = SignalResolver(dependencyResolver)
    private var componentToExecute: FeatureComponent? = null
    private val onDemandComponents = arrayListOf<String>()
    private lateinit var router: FeatureRouter


    override fun onSignalReceived(signal: String, component: FeatureComponent) {
        processSignal(signal, component)
    }

    override fun registerRouter(router: FeatureRouter) {
        this.router = router
    }

    override fun startEngine(features: Feature) {
        Log.d(
            "AppInitStateMachine",
            "State machine started with:\n\t ${features.featureComponents}, size: ${features.featureComponents.size}"
        )
        for (id in features.featureConfig.onDemandComponents) {
            onDemandComponents.add(id)
        }

        for (component in features.featureComponents) {
            if (!onDemandComponents.contains(component.componentId)) {
                processSignal(component.componentStateMachine.start, component)
            }
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
                router.route(component.componentId, component, this)
            }
        }
    }

}