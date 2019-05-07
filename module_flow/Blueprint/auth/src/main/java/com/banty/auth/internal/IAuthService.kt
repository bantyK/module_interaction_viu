package com.banty.auth.internal

/**
 * Created by Banty on 2019-05-06.
 */
interface IAuthService {
    fun stateChanged(authState : AuthState)
}