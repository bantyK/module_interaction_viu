package com.banty.amplitudeanalyticscomponent

import android.util.Log
import com.banty.analyticsinterface.IAnalytics
import org.json.JSONObject

/**
 * Created by Banty on 2019-04-29.
 */
class AnalyticsAmplitudeComponent : IAnalytics {
    override fun configure() {
        Log.d("Banty", "Viu Analytics Configuration done.")
    }

    override fun trackEvent(eventName: String, eventProps: JSONObject) {
        Log.d("Banty", "reportEvent(Amplitude) --- $eventName { $eventProps }")
    }

    override fun identify(userProps: JSONObject) {
        Log.d("Banty", "setUserProperties(Amplitude) --- $userProps")
    }
}