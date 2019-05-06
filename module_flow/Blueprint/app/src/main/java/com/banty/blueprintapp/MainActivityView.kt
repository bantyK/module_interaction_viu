package com.banty.blueprintapp

import com.banty.core.ViuComponent

interface MainActivityView {
    fun navigateTo(module: ViuComponent, payload: HashMap<String, Any>)
}
