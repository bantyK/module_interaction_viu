package com.banty.config

import org.json.JSONObject
import javax.inject.Inject

/**
 * Created by Banty on 2019-05-02.
 */
class HomeFlowConfig @Inject constructor() {

    fun getHomeFlowConfig(): JSONObject {
        val jsonObject = JSONObject()

        jsonObject.put("show_recently_watched", true)
        jsonObject.put("show_watchlist", false)
        jsonObject.put("show_download_button", true)
        jsonObject.put("launch_player_on_click", false)

        return jsonObject
    }
}