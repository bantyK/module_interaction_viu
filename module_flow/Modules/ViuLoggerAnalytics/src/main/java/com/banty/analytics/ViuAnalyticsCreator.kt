package com.banty.analytics

import com.banty.moduleinterface.ImplCreator
import com.banty.analyticsinterface.IAnalytics

/**
 * Created by Banty on 26/04/19.
 */
class ViuAnalyticsCreator : ImplCreator<IAnalytics> {

    override fun createImpl(): IAnalytics {
        return ViuAnalytics()
    }
}