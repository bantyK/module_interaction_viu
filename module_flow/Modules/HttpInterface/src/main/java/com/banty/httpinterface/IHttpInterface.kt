package com.banty.httpinterface

import org.json.JSONObject

/**
 * Created by Banty on 2019-04-29.
 */
interface IHttpInterface {

    fun get(url: String)

    fun post(url: String, requestBody: JSONObject)
}