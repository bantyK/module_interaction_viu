package detail

import base.Flow
import base.FlowPriority

class DetailFlow : Flow() {
    override fun getPriority(): FlowPriority =
        FlowPriority.HIGH


    override fun getStartSignal() = "CLIP_DETAIL"


    override fun getEndSignal() =
        "DETAIL_END"


    override fun start(context: String) {
        println("Flow launched ${this.javaClass.simpleName}")
    }
}