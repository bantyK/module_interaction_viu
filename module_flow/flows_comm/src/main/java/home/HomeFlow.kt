package home

import base.Columbus
import base.Flow
import base.FlowPriority
import base.Signal
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeFlow : Flow() {
    override fun getStartSignal(): String {
        return "SHOW_HOME"
    }

    override fun getEndSignal(): String {
        return "APP_END"
    }

    override fun getPriority(): FlowPriority {
        return FlowPriority.LOW
    }

    override fun start(context: String) {
        println("Flow launched ${this.javaClass.simpleName}")

        GlobalScope.launch {
            delay(600)
            Columbus.getColumbus().submit(Signal("PLAY", "PLAY_COMPLETE", this@HomeFlow))
        }
    }
}