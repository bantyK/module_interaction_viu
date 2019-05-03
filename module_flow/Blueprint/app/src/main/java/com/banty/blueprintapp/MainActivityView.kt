package com.banty.blueprintapp

import com.banty.core.Flow

interface MainActivityView {
    fun navigateTo(flow: Flow, payload:HashMap<String, Any>)
}
