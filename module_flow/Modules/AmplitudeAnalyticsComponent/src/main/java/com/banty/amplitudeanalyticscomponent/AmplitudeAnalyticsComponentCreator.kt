package com.banty.amplitudeanalyticscomponent

import com.banty.analyticsinterface.IAnalytics
import com.banty.moduleinterface.IImplCreator

/**
 * Created by Banty on 2019-04-29.
 */
class AmplitudeAnalyticsComponentCreator : IImplCreator<IAnalytics>{

    override fun createImpl(): IAnalytics {
        return AnalyticsAmplitudeComponent()
    }

}