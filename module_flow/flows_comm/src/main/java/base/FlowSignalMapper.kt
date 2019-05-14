package base

import detail.DetailFlow
import home.HomeFlow
import player.PlayerFlow
import subs.SubscriptionFlow

class FlowSignalMapper {

    fun getFlowFromSignal(signal: String): Flow {
        when (signal) {
            "SHOW_HOME" -> return HomeFlow()
            "PLAY" -> return PlayerFlow()
            "SUBS_REQ" -> return SubscriptionFlow()
            "CLIP_DETAIL" -> return DetailFlow()
        }
        throw NoFlowFoundException("Flow not available to handle signal : $signal")
    }

}
