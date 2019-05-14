package player

import base.Columbus
import base.Flow
import base.RouteEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import subs.UserSubStatus

class PlayerFlow : Flow() {

    override fun start(context: String) {
        println("Player flow launched")

        launch {
            delay(1_000)
            if(!UserSubStatus.isPremium()) {
                println("Launching subscription flow")
                Columbus.getColumbus().route(
                    RouteEvent(
                        "SUBS_REQ",
                        "SUB_STATUS",
                        this@PlayerFlow
                    )
                )
            } else {
                println("")
            }
        }
    }
}