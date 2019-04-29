package com.banty.okhttpcomponent

import android.util.Log
import com.banty.httpinterface.IHttpInterface
import org.json.JSONObject

/**
 * Created by Banty on 2019-04-29.
 */
class OkHttpComponent : IHttpInterface {
    override fun get(url: String) {
        Log.d("Banty", "Get call with url $url")
    }

    override fun post(url: String, requestBody: JSONObject) {
        Log.d("Banty", "Post request with url $url and request body ${requestBody}")
    }
}