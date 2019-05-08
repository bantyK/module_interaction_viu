package com.banty.auth.services

import kotlinx.coroutines.delay

/**
 * Created by Banty on 2019-05-06.
 */
class BillingService {

    suspend fun getBillingStatus(userId: String): String {
//        delay(1_000)
        return "PREMIUM"
    }
}