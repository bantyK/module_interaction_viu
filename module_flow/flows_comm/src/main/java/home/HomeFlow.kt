package home

import base.Columbus
import base.Flow
import base.RouteEvent
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeFlow : Flow() {

    override fun start(context: String) {
        println("Home flow started")

        GlobalScope.launch {
            delay(1_000)
            println("Launching player flow")
            Columbus.getColumbus().route(
                RouteEvent("PLAY", "PLAY_END", this@HomeFlow)
            )
        }
    }
}