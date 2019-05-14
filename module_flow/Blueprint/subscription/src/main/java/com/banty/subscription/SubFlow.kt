package com.banty.subscription

import android.content.Context
import com.banty.columbus.Columbus
import com.banty.core.Flow
import com.banty.core.signal.Signal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SubFlow : Flow {

    override fun start(context: Context, payload: HashMap<String, Any>) {
        val pricePoint = payload["price_point"] as String?
        val subsPartner = payload["subs_partner"] as String?
        GlobalScope.launch (Dispatchers.Main){
            val isSubscriptionSuccessful = SubscriptionService().subscribe(pricePoint, subsPartner)
            val payload = HashMap<String, Any>()
            payload["subs_status"] = isSubscriptionSuccessful
            Columbus.getColumbus().postEvent(Signal.SUBS_STATUS, payload)
        }
    }

    override fun await(signal: Signal) {

    }
}