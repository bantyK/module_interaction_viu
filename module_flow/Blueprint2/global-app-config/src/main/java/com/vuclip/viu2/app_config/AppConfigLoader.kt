package com.vuclip.viu2.app_config

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.vuclip.viu2.app_config.model.feature.AppConfig
import com.vuclip.viu2.app_config.model.feature.Feature
import java.io.*


class AppConfigLoader {

    companion object {
        lateinit var jsonString: String

        private var instance: AppConfigLoader? = null

        fun getInstance(): AppConfigLoader {
            if (instance == null) {
                instance = AppConfigLoader()
            }
            return instance!!
        }
    }

    fun init(context: Context) {
        jsonString = loadJsonFromFile(context)
    }

    private fun loadJsonFromFile(context: Context): String {
        var ret = ""
        try {
            val inputStream: InputStream? = context.resources.openRawResource(R.raw.app_config)
            if (inputStream != null) {
                val inputStreamReader = InputStreamReader(inputStream)
                val bufferedReader = BufferedReader(inputStreamReader)
                val stringBuilder = StringBuilder()
                var readString = bufferedReader.readLine()
                while (readString != null) {
                    stringBuilder.append(readString)
                    readString = bufferedReader.readLine()
                }
                inputStream.close()
                ret = stringBuilder.toString()
            }
        } catch (e: FileNotFoundException) {
            Log.e("readFromFile", "File not found: $e")
        } catch (e: IOException) {
            Log.e("readFromFile", "Can not read file: $e")
        }

        return ret
    }

    private fun getAppConfig(): AppConfig? {
        return Gson().fromJson(jsonString, AppConfig::class.java)
    }

    fun getLauncher(): String? {
        return getAppConfig()?.app?.appConfig?.launchFeature
    }

    fun getFeatureConfig(featureId: String): Feature? {

        val appFeatures = getAppConfig()?.features
        if (appFeatures != null && appFeatures.isNotEmpty()) {
            for (feature in appFeatures) {
                if (feature.featureId == featureId) {
                    return feature
                }
            }
        }

        return null
    }

}