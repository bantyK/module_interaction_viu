package base

class SignalResolver(private val queueManager: QueueManager) {

    private val flowSignalMapper = FlowSignalMapper()

    private var activeFlow: Flow? = null

    fun handle(currentSignal: Signal): Flow? {
        println("Handle signal:\n\t$currentSignal")

        // check if there is any flow which is waiting for the currentSignal, if there is, then handle that flow first.
        // if there isn't any waiting flow then get the most prioritised signal from the queue manager.
        val signalToBeProcessed: Signal? = if (queueManager.signalPresentInWaitingQueues(currentSignal))
            currentSignal
        else
            queueManager.getPrioritySignal(currentSignal)

        println("Highest priority signal to be processed:\n\t$signalToBeProcessed")

        var flow: Flow? = queueManager.findWaitingFlow(signalToBeProcessed!!)

        if (flow != null) {
            println("\tWaiting flow found:\n\t$flow")
        } else {
            flow = flowSignalMapper.getFlowFromSignal(signalToBeProcessed.startSignal)
            println("\tNo waiting flow, launching new: $flow")
        }

        // current signal's priority is less than or equal to active flow's
        println("Active flow $activeFlow\nNew flow $flow")
        if (activeFlow != null && flow.getPriorityLevel() < (activeFlow as Flow).getPriorityLevel()) {
            println("High priority flow is active. \n\t Holding $flow")
            return null
        } else {
            // update the waiting map now.
            queueManager.addSignal(

            )

        }

        // update the queues - update WAIT-MAP, remove consumed signals or flows
        removeSignal(signalToBeProcessed, currentSignal)

        activeFlow = flow
        println("Currently Active flow:\n\t$activeFlow")

        return flow
    }

    private fun removeSignal(signalToBeProcessed: Signal, currentSignal: Signal) {
        println("Remove signal\n\t to be processed $signalToBeProcessed\t\t$currentSignal")

        val signalToRemove = queueManager.findWaitingSignal(signalToBeProcessed)

        println("Remove signal\n\t to be removed $signalToRemove")

        when {
            signalToRemove != null -> queueManager.removeSignal(signalToRemove)
            signalToBeProcessed.waitingSignal == null -> queueManager.removeSignal(signalToBeProcessed)
            currentSignal.startSignal == signalToBeProcessed.waitingSignal -> queueManager.removeSignal(signalToBeProcessed)
        }
    }


}