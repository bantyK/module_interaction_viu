package com.banty.config

import org.json.JSONObject

/**
 * Created by Banty on 2019-05-02.
 */
object AppInitConfig {

    fun getAppInitConfig() : JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put("detect_location", false)
        jsonObject.put("base_url","https://prod-in.viu.in/")
        jsonObject.put("graphql_client","appolo")
        jsonObject.put("show_upgrade_popup", false)
        return jsonObject
    }
}