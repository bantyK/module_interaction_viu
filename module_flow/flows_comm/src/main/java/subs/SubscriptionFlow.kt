package subs

import base.Columbus
import base.Flow
import base.FlowPriority
import base.Signal
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SubscriptionFlow : Flow() {

    override fun start(context: String) {
        println("Flow launched ${this.javaClass.simpleName}")
        startSubscription()
    }

    private fun startSubscription() {
        println("Subscription complete")
        launch {
            delay(5_00)
            UserSubStatus.setPremium()
            Columbus.getColumbus().submit(
                Signal(
                    "SUB_STATUS",
                    null,
                    null
                )
            )
        }
    }

    override fun getStartSignal(): String  = "SUB_REQ"

    override fun getEndSignal(): String  = "SUB_STATUS"

    override fun getPriority(): FlowPriority = FlowPriority.LOW
}