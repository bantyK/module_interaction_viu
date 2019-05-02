package com.banty.config

import org.json.JSONObject

/**
 * Created by Banty on 2019-05-02.
 */
class AppInitConfig {

    fun getAppInitConfig() : JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put("app_init_detect_location", false)
        jsonObject.put("app_init_base_url","https://prod-in.viu.in/")
        jsonObject.put("app_init_graphql_client","appolo")
        jsonObject.put("app_init_show_upgrade_popup", false)
        return jsonObject
    }
}