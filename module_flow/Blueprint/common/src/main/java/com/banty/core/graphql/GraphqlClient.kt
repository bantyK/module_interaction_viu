package com.banty.core.graphql

import com.banty.core.model.Clip
import org.json.JSONObject
import javax.inject.Inject

/**
 * Created by Banty on 2019-05-02.
 */
const val ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
const val thumbUrl = "https://technology.inquirer.net/files/2016/11/Viu-icon.png"
class GraphqlClient @Inject constructor() {

    fun provideHomeFeed(): List<Clip> {
        return listOf(
                Clip("1", "Clip 1", false, thumbUrl),
                Clip("2", "Clip 2", true, thumbUrl),
                Clip("3", "Clip 3", false, thumbUrl)
        )
    }


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