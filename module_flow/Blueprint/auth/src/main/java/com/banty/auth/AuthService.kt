package com.banty.auth

import android.content.Context
import android.util.Log
import com.banty.auth.services.BillingService
import com.banty.auth.services.UserService
import com.banty.columbus.Columbus
import com.banty.core.signal.Signal

/**
 * Created by Banty on 2019-05-06.
 *
 * The main class which performs the authentication service and finally create the Viu user object.
 */
class AuthService {

    suspend fun startUserAuthentication(context: Context, payload: HashMap<String, Any>) {
        val userId = UserService().getUserId()
        val billingStatus = BillingService().getBillingStatus(userId)
        Log.d("Viu", "Billing status : ${billingStatus}")

        val authPayload = HashMap<String, Any>()
        payload["billing_status"] = billingStatus

        Columbus.getColumbus().postEvent(Signal.SHOW_HOME, authPayload)
        // Set the billing status to a global user component
    }
}
