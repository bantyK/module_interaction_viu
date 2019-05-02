package com.banty.init.app_init

/**
 * Created by Banty on 2019-05-02.
 */
enum class AppInitStates {
    CHECK_APP_UPGRADE,
    LOCATION,
    PROGRAMMING,
    SECURITY
}

enum class Status {
    SUCCESS,
    FAILED,
    OP_NOT_REQUIRED
}