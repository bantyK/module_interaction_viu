package base

/*  When a flow generate a routeEvent, the parameters indicates the following
    nextSignal -> signal which indicates the flow which the existing flow want to trigger
    waitingSignal -> signal which indicates the flow which the flow might wait for

 */
data class RouteEvent(
    val nextSignal: String,
    val waitingSignal: String?, // this can be an array in production because a flow might wait for more than one flow
    val flow: Flow?,
    val priority: Int = 10
) {
    override fun toString(): String {
        return "Route : nextSignal -> $nextSignal Waiting signal -> $waitingSignal Flow -> ${flow.toString()} priority -> $priority"
    }
}