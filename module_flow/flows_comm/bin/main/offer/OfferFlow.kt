package offer

import base.Flow
import base.FlowPriority

class OfferFlow : Flow(){
    override fun getStartSignal(): String {
        return "SHOW_OFFER"
    }

    override fun getEndSignal(): String {
        return "OFFER_END"
    }

    override fun getPriorityLevel(): FlowPriority {
        return FlowPriority.LOW
    }

    override fun start(context: String) {
//        println("Flow launched ${this.javaClass.simpleName}")
    }

}