package com.banty.init.app_init

import dagger.Component

/**
 * Created by Banty on 2019-05-02.
 */
interface AppInitStateMachine {
    fun stateChanged(state: InitStates, status: Status)
}
