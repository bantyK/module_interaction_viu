package com.banty.auth.services

import kotlinx.coroutines.delay

/**
 * Created by Banty on 2019-05-06.
 */
class UserService() {

    suspend fun getUserId() : String {
        delay(1_000)
        return "u-123"
    }

}