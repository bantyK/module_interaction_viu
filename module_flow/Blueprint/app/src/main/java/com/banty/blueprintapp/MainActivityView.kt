package com.banty.blueprintapp

import com.banty.core.Flow

interface MainActivityView {
    fun navigateTo(module: Flow, payload: HashMap<String, Any>)
}
