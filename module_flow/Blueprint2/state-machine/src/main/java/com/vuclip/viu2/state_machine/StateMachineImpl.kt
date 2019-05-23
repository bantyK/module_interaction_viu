package com.vuclip.viu2.state_machine

import android.util.Log
import com.vuclip.viu2.app_config.model.feature.Feature
import com.vuclip.viu2.app_config.model.feature.FeatureComponent
import com.vuclip.viu2.base.FeatureRouter
import com.vuclip.viu2.base.SignalDispatcher
import com.vuclip.viu2.base.StateMachine
import com.vuclip.viu2.state_machine.util.SupportedSignalUtility
import java.util.concurrent.Executors

class StateMachineImpl : StateMachine, SignalDispatcher {
    private val dependencyResolver = DependencyResolver()

    private val signalResolver = SignalResolver(dependencyResolver)
    private var componentToExecute: FeatureComponent? = null
    private val onDemandComponents = arrayListOf<String>()
    private val supportedComponents = arrayListOf<String>()
    private val pendingForExecution = arrayListOf<String>()

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
            pendingForExecution.remove(component.componentId)
        } else {
            val dependencies = dependencyResolver.getDependencies(component.componentProps)
            signalResolver.add(QueueItem(signal, component, dependencies))
            resolveDependencies(signal,dependencies)
            Log.d("AppInitStateMachine", "Dependencies for $signal:\n\t $dependencies")
        }

        componentToExecute = signalResolver.getInvokable()

        if (componentToExecute == null) {
            Log.d("AppInitStateMachine", "Nothing ready to execute")
        } else {
            pendingForExecution.add(componentToExecute!!.componentId)
            executeSignal(componentToExecute)
        }
    }

    private fun resolveDependencies(signal: String, dependencies: ArrayList<String>) {

        for (dependency in dependencies) {
            // check if the dependency is not present in current feature
            if (!supportedComponents.contains(dependency)) {
                // submit the signal to columbus
            } else {
                // check if dependent component is not in progress
                if(!pendingForExecution.contains(dependency)) {
                    router.route(signal,)
                }
            }
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

    override fun setSupportedDependencies(feature: Feature) {
        SupportedSignalUtility.storeSupportedComponents(supportedComponents, feature)
    }
}