package com.banty.analyticsinterface

import org.json.JSONObject

/**
 * Created by Banty on 26/04/19.
 */
interface IAnalytics {

    fun configure()

    fun trackEvent(eventName: String, eventProps: JSONObject)

    fun identify(userProps: JSONObject)
}
