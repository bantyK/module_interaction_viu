package com.banty.blueprintapp

import androidx.fragment.app.Fragment
import com.banty.core.Flow

interface MainActivityView {
    fun navigateTo(flow: Flow)
}
