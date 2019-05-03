package com.banty.init.app_init

import com.banty.columbus.Columbus
import com.banty.core.signal.Signal
import com.banty.init.di.AppInitComponent
import com.banty.init.di.DaggerAppInitComponent
import kotlinx.coroutines.delay

/**
 * Created by Banty on 2019-05-02.
 */
class SplashFragmentPresenter(private val appInitStateMachine: AppInitStateMachine) {

    private val initComponent: AppInitComponent = DaggerAppInitComponent.builder().build()

    private val locationService = initComponent.injectLocation()

    private val securityService = initComponent.injectSecurityService()

    private val programming = initComponent.injectProgramming()

    private val appInitConfig = initComponent.injectAppInitConfig()

    suspend fun checkAppUpgrade() {
        delay(1_000)
        if (appInitConfig.getAppInitConfig().optBoolean("app_init_show_upgrade_popup")) {
            appInitStateMachine.stateChanged(AppInitStates.CHECK_APP_UPGRADE, Status.FAILED)
        } else {
            appInitStateMachine.stateChanged(AppInitStates.CHECK_APP_UPGRADE, Status.SUCCESS)
        }
    }

    suspend fun checkLocation() {
        delay(1_000)
        if (appInitConfig.getAppInitConfig().optBoolean("app_init_detect_location")) {
            locationService.getLocation()
            appInitStateMachine.stateChanged(AppInitStates.LOCATION, Status.SUCCESS)
        } else {
            appInitStateMachine.stateChanged(AppInitStates.LOCATION, Status.OP_NOT_REQUIRED)
        }
    }

    suspend fun getProgramming() {
        delay(1_000)
        programming.getHomeUrl()
        appInitStateMachine.stateChanged(AppInitStates.PROGRAMMING, Status.SUCCESS)
    }

    suspend fun getSecurity() {
        delay(1_000)
        securityService.getToken()
        appInitStateMachine.stateChanged(AppInitStates.SECURITY, Status.SUCCESS)
    }

    fun sendAppInitSuccessSignal() {
        Columbus.getColumbus().postEvent(Signal.SHOW_HOME)
    }

    fun stateAppInit() {
        appInitStateMachine.stateChanged(AppInitStates.CHECK_NETWORK, Status.SUCCESS)
    }

}