package base

class SignalResolver(private val queueManager: QueueManager) {

    private val flowSignalMapper = FlowSignalMapper()

    private var activeFlow: Flow? = null

    fun handle(currentSignal: Signal): Flow? {
        // check if there is any flow which is waiting for the currentSignal, if there is, then handle that flow first.
        // if there isn't any waiting flow then get the most prioritised signal from the queue manager.
        val signalToBeProcessed: Signal? = if (queueManager.signalPresentInWaitingQueues(currentSignal))
            currentSignal
        else
            queueManager.getPrioritySignal()

        println("Highest priority signal to be processed:\n\t$signalToBeProcessed")

        var flow: Flow? = queueManager.findWaitingFlow(signalToBeProcessed!!)

        if (flow != null) {
            println("\tWaiting flow found:\n\t$flow")
        } else {
            flow = flowSignalMapper.getFlowFromSignal(signalToBeProcessed.startSignal!!)
            println("\tNo waiting flow, launching new: $flow")
        }

        // current signal's priority is less than or equal to active flow's
        if (activeFlow != null && flow != null && flow.getPriorityLevel() < (activeFlow as Flow).getPriorityLevel()) {
            println("High priority flow is active. \n\t Holding $flow")
            return null
        } else {
            // update the waiting map now.
            println("$flow has more priority than $activeFlow")
            // flow's ending signal should launch the active flow
        }

        // update the queues - update WAIT-MAP, remove consumed signals or flows
        removeSignal(signalToBeProcessed, currentSignal)

        activeFlow = flow
        println("Active flow $activeFlow\n")

        if (flow == null)
            handle(queueManager.getPrioritySignal()!!) //process next signal in queue

        return flow
    }

    private fun removeSignal(signalToBeProcessed: Signal, currentSignal: Signal) {
        val signalToRemove = queueManager.findWaitingSignal(signalToBeProcessed)
        when {
            signalToRemove != null -> queueManager.removeSignal(signalToRemove)
            signalToBeProcessed.waitingSignal == null -> queueManager.removeSignal(signalToBeProcessed)
            currentSignal.startSignal == signalToBeProcessed.waitingSignal -> queueManager.removeSignal(
                signalToBeProcessed
            )
        }
    }


}