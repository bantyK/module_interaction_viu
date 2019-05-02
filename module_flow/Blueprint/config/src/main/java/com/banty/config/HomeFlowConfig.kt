package com.banty.config

import org.json.JSONObject
import javax.inject.Inject

/**
 * Created by Banty on 2019-05-02.
 */
class HomeFlowConfig @Inject constructor() {

    fun getHomeFlowConfig(): JSONObject {
        val jsonObject = JSONObject()

        jsonObject.put("home_flow_show_recently_watched", true)
        jsonObject.put("home_flow_show_watchlist", true)
        jsonObject.put("home_flow_show_download_button", true)
        jsonObject.put("home_flow_launch_player_on_click", false)

        return jsonObject
    }
}