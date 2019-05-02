package com.banty.init.app_init

/**
 * Created by Banty on 2019-05-02.
 */
interface AppInitStateMachine {
    fun stateChanged(state: AppInitStates, status: Status)
}
