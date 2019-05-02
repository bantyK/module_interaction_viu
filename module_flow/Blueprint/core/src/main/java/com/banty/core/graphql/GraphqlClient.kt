package com.banty.core.graphql

import org.json.JSONObject

/**
 * Created by Banty on 2019-05-02.
 */
const val ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

class GraphqlClient {

    fun getDataFromBFF(url: String, keys: List<String>): JSONObject {
        val jsonObject = JSONObject()
        for (key in keys) {
            jsonObject.put(key, randomString())
        }

        return jsonObject
    }

    fun randomString(): String {
        var count = 5
        val builder = StringBuilder()
        while (count-- != 0) {
            val character = (Math.random() * ALPHA_NUMERIC_STRING.length).toInt()
            builder.append(ALPHA_NUMERIC_STRING[character])
        }
        return builder.toString()
    }
}