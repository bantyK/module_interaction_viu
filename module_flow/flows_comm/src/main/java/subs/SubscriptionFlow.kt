package subs

import base.Columbus
import base.Flow
import base.RouteEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SubscriptionFlow : Flow() {

    override fun start(context: String) {
        println("Launching subscription flow")

        startSubscription()
    }

    private fun startSubscription() {
        println("Subscription complete")
        launch {
            delay(5_00)
            Columbus.getColumbus().route(
                RouteEvent(
                    "SUB_STATUS",
                    null,
                    null
                )
            )
        }
    }
}