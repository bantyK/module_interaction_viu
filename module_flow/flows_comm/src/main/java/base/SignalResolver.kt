package base

import java.util.*

class SignalResolver(private val queueManager: QueueManager) {

    private var activeFlow: Flow? = null

    private val highSignalQueue = queueManager.highFlowQueue
    private val mediumSignalQueue = queueManager.mediumFlowQueue
    private val lowSignalQueue = queueManager.lowFlowQueue

    private val flowSignalMapper = FlowSignalMapper()

    fun handle(currentSignal: Signal): Flow? {
        println("Handle signal:\n\t$currentSignal")

        val highSignal: Signal? = if (queueManager.signalPresentInWaitingQueues(currentSignal)) currentSignal else getPrioritySignal(currentSignal)

        println("Highest priority signal to be processed:\n\t$highSignal")

        var flow: Flow? = queueManager.findWaitingFlow(highSignal!!)
        if (flow != null) {
            println("\tWaiting flow found:\n\t$flow")
        } else {
            flow = flowSignalMapper.getFlowFromSignal(highSignal?.startSignal!!)
            println("\tNo waiting flow, launching new: $flow")
        }

        // update the queues - update WAIT-MAP, remove consumed signals or flows

        removeSignal(highSignal, currentSignal)

        return flow

    }

    private fun removeSignal(highSignal: Signal, currentSignal: Signal) {
        if(queueManager.signalPresentInWaitingQueues(currentSignal)) {

        }
        if (highSignal.waitingSignal == null) {
            queueManager.removeSignal(highSignal)
        } else if (currentSignal.startSignal == highSignal.waitingSignal) {
            queueManager.removeSignal(highSignal)
        } else {
    //            updateMapping()
        }
    }

    private fun getPrioritySignal(currentSignal: Signal): Signal? {
        //check if some flow is waiting, if yes - process signal right away

        var signal = getSignalFromQueue(highSignalQueue)

        if (signal == null) signal = getSignalFromQueue(mediumSignalQueue)

        if (signal == null) signal = getSignalFromQueue(lowSignalQueue)

        return signal
    }

    private fun getSignalFromQueue(queue: Stack<Signal>): Signal? =
        if (queue.isNotEmpty()) queue.peek() else null


}