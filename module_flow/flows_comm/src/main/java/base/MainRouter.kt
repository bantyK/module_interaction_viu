package base

class MainRouter(
    private val flowManager: FlowManager,
    private val flowSignalMapper: FlowSignalMapper
) : Router {
    override fun route(routeEvent: RouteEvent) {
        println("$routeEvent")
        updateFlowSignalMap(routeEvent)
        // check waiting map first, if there is a flow which is waiting for this signal
        if (flowManager.isFlowWaitingForSignal(routeEvent.nextSignal)) {
            handleWaitingFlow(routeEvent)
        } else {
            flowSignalMapper.getFlowFromSignal(routeEvent.nextSignal).start("activity")
        }
    }

    private fun handleWaitingFlow(routeEvent: RouteEvent) {
        val flow = flowManager.getFlow(routeEvent.nextSignal)
        if (flow != null)
            flow.start("activity")
        else
            throw NoFlowFoundException("Flow must be specified")
    }


    override fun addRoute(routeEvent: RouteEvent) {
        updateFlowSignalMap(routeEvent)
    }


    private fun updateFlowSignalMap(routeEvent: RouteEvent) {
        if (routeEvent.waitingSignal != null) {
            if (routeEvent.flow != null) {
                flowManager.addFlow(routeEvent.waitingSignal, routeEvent.flow)
            } else {
                throw NoFlowFoundException("Flow must be specified")
            }
        }
    }
}
