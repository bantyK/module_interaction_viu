package base

import detail.DetailFlow
import home.HomeFlow
import offer.OfferFlow
import player.PlayerFlow
import subs.SubscriptionFlow

class FlowSignalMapper {

    fun getFlowFromSignal(signal: String): Flow {
        when (signal) {
            "SHOW_HOME" -> return HomeFlow()
            "PLAY" -> return PlayerFlow()
            "SUBS_REQ" -> return SubscriptionFlow()
            "CLIP_DETAIL" -> return DetailFlow()
            "SHOW_OFFER" -> return OfferFlow()
        }
        throw NoFlowFoundException("Flow not available to handle signal : $signal")
    }

}
