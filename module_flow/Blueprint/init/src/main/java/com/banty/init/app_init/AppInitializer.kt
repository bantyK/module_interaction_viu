package com.banty.init.app_init

import com.banty.config.AppInitConfig
import com.banty.init.di.AppInitComponent
import com.banty.init.di.DaggerAppInitComponent

/**
 * Created by Banty on 2019-05-02.
 */
class AppInitializer(val appInitConfig: AppInitConfig, val appInitStateMachine: AppInitStateMachine) {

    private val initComponent: AppInitComponent = DaggerAppInitComponent.builder().build()

    private val locationService = initComponent.injectLocation()

    private val securityService = initComponent.injectSecurityService()

    private val programming = initComponent.injectProgramming()


    fun startInit() {

        if (appInitConfig.getAppInitConfig().optBoolean("app_init_detect_location")) {
            locationService.getLocation()
            appInitStateMachine.stateChanged(InitStates.LOCATION, Status.SUCCESS)
        } else {
            appInitStateMachine.stateChanged(InitStates.LOCATION, Status.OP_NOT_REQUIRED)
        }
    }

    fun getProgramming() {
        programming.getHomeUrl()
        appInitStateMachine.stateChanged(InitStates.PROGRAMMING, Status.SUCCESS)
    }

    fun getSecurity() {
        securityService.getToken()
        appInitStateMachine.stateChanged(InitStates.SECURITY, Status.SUCCESS)
    }

}