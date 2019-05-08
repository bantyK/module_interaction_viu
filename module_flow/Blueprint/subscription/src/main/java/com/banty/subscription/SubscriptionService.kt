package com.banty.subscription

import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SubscriptionService {
    suspend fun subscribe(pricePoint: String?, subsPartner: String?) : Boolean{
        delay(1_000)
        Log.d("Viu", "Subscribing $pricePoint via $subsPartner")
        return true
    }

}
