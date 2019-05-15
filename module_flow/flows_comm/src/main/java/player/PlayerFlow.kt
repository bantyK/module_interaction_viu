package player

import base.Columbus
import base.Flow
import base.FlowPriority
import base.Signal
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import subs.UserSubStatus

class PlayerFlow : Flow() {
    override fun start(context: String) {
        println("Flow launched ${this.javaClass.simpleName}")

        launch {
            delay(1_000)
            if (!UserSubStatus.isPremium()) {
                Columbus.getColumbus().submit(
                    Signal(
                        "SUBS_REQ",
                        "SUB_STATUS",
                        this@PlayerFlow
                    )
                )
            } else {
                println("Player flow : Playing the clip")
//                Columbus.getColumbus().submit(
//                    Signal(
//                        "PLAY_COMPLETE",
//                        null,
//                        null
//                    )
//                )
            }
        }
    }

    override fun getStartSignal(): String = "PLAY"

    override fun getEndSignal(): String = "PLAY_COMPLETE"

    override fun getPriority(): FlowPriority = FlowPriority.LOW
}
