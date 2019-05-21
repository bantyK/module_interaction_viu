package detail

import base.Columbus
import base.Flow
import base.FlowPriority
import base.Signal

class DetailFlow : Flow() {
    override fun getPriorityLevel(): FlowPriority =
        FlowPriority.HIGH


    override fun getStartSignal() = "CLIP_DETAIL"


    override fun getEndSignal() =
        "DETAIL_END"


    override fun start(context: String) {
        println("Flow launched ${this@DetailFlow.javaClass.simpleName}")
        Columbus.getColumbus().submit(
            Signal(getEndSignal(), null, null)
        )
    }
}