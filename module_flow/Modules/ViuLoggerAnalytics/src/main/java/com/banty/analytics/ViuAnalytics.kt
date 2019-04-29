package com.banty.analytics

import android.util.Log
import com.banty.analyticsinterface.IAnalytics
import org.json.JSONObject

/**
 * Created by Banty on 26/04/19.
 */
class ViuAnalytics : IAnalytics {

    override fun configure() {
        Log.d("Banty", "Viu Analytics Configuration done.")
    }

    override fun trackEvent(eventName: String, eventProps: JSONObject) {
        Log.d("Banty", "reportEvent --- $eventName { $eventProps }")
    }

    override fun identify(userProps: JSONObject) {
        Log.d("Banty", "setUserProperties --- $userProps")
    }
}