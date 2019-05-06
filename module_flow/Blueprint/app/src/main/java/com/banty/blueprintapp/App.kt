package com.banty.blueprintapp

import android.app.Application
import com.banty.blueprintapp.mapper.ObjectMapper

/**
 * Created by Banty on 2019-05-02.
 */
class App : Application() {

    private lateinit var objectMapper: ObjectMapper

    override fun onCreate() {
        super.onCreate()
        ContextProvider.getInstance().setContext(this.applicationContext)
        val inputStream = this.resources
                .openRawResource(R.raw.navigator)
        objectMapper = ObjectMapper.getObjectMapper(inputStream).prepareMap()
    }

    fun getObjectMapper(): ObjectMapper {
        return objectMapper
    }
}