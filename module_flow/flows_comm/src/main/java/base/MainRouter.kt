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
            flowManager.getFlow(routeEvent.nextSignal).start("activity")
        } else {
            flowSignalMapper.getFlowFromSignal(routeEvent.nextSignal).start("activity")
        }
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
