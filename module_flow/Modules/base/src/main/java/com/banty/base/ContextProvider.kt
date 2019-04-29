package com.banty.base

import android.content.Context

/**
 * Created by Banty on 26/04/19.
 */
class ContextProvider {
    companion object {

        private var appContext: Context? = null

        private val instance = ContextProvider()

        fun getInstance() = instance
    }

    fun setContext(context: Context) {
        appContext = context
    }

    fun getContext() = appContext
}