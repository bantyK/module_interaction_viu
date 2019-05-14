package deeplink

import base.Columbus
import base.RouteEvent
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DeeplinkListener {

    fun handleDeeplinkPayload() {
        GlobalScope.launch {
            delay(1_200)
        }
    }
}