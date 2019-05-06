package com.banty.blueprintapp

import android.content.Context

/**
 * Created by Banty on 2019-05-06.
 */
class ContextProvider {
    private var applicationContext: Context? = null

    companion object {
        private var INSTANCE: ContextProvider? = null

        fun getInstance(): ContextProvider {
            synchronized(ContextProvider::class) {
                if (INSTANCE == null) {
                    INSTANCE = ContextProvider()
                }
            }

            return INSTANCE!!
        }
    }

    fun setContext(appContext: Context) {
        this.applicationContext = appContext
    }

    fun getContext(): Context {
        if (applicationContext == null)
            throw IllegalAccessException("Context is not set. Set the context first in application file")
        else
            return applicationContext!!
    }
}