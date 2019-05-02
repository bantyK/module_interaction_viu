package com.banty.init.app_init

import com.banty.init.di.AppInitComponent
import com.banty.init.di.DaggerAppInitComponent

/**
 * Created by Banty on 2019-05-02.
 */
class SplashFragmentPresenter(val appInitStateMachine: AppInitStateMachine) {

    private val initComponent: AppInitComponent = DaggerAppInitComponent.builder().build()

    private val locationService = initComponent.injectLocation()

    private val securityService = initComponent.injectSecurityService()

    private val programming = initComponent.injectProgramming()

    private val appInitConfig = initComponent.injectAppInitConfig()

    fun startInit() {
        if (appInitConfig.getAppInitConfig().optBoolean("app_init_show_upgrade_popup")) {
            appInitStateMachine.stateChanged(InitStates.CHECK_APP_UPGRADE, Status.FAILED)
        } else {
            appInitStateMachine.stateChanged(InitStates.CHECK_APP_UPGRADE, Status.SUCCESS)
        }
    }

    fun checkLocation() {
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