package com.banty.init.security

import javax.inject.Inject

/**
 * Created by Banty on 2019-05-02.
 */
class SecurityService @Inject constructor() {

    fun getToken(): String {
        return "viu::123"
    }
}