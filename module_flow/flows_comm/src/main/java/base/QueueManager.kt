package base

import java.util.*

class QueueManager {

    val highFlowQueue = Stack<Signal>()

    val mediumFlowQueue = Stack<Signal>()

    val lowFlowQueue = Stack<Signal>()

    fun submitSignal(signal: Signal) {
        println("\n\nNew signal: \n\t$signal")
        when {
            signalPresentInWaitingQueues(signal) -> println("Signal found in waiting:\n\t$signal")

            signal.waitingFlow == null -> lowFlowQueue.add(signal)

            else -> addInWaitingQueue(signal.waitingFlow, signal)
        }

        printQueues()
    }

    private fun addInWaitingQueue(waitingFlow: Flow, signal: Signal) {
        when (waitingFlow.getPriority()) {
            FlowPriority.LOW -> lowFlowQueue.add(signal)
            FlowPriority.MEDIUM -> mediumFlowQueue.add(signal)
            FlowPriority.HIGH -> highFlowQueue.add(signal)
        }
    }

    fun signalPresentInWaitingQueues(signal: Signal): Boolean {
        return scanWaitingSignal(signal, highFlowQueue)
                || scanWaitingSignal(signal, mediumFlowQueue)
                || scanWaitingSignal(signal, lowFlowQueue)
    }

    private fun scanWaitingSignal(signal: Signal, stack: Stack<Signal>): Boolean {
        for (item in stack) {
            if (item.waitingSignal == signal.startSignal) {
                return true
            }
        }
        return false
    }

    fun findWaitingFlow(signal: Signal): Flow? {
        println("Any flow waiting for \"${signal.startSignal}\"?")

        var flow = findWaitingFlow(signal, lowFlowQueue)

        if (flow == null) {
            flow = findWaitingFlow(signal, mediumFlowQueue)
        }

        if (flow == null) {
            flow = findWaitingFlow(signal, highFlowQueue)
        }

        return flow
    }

    private fun findWaitingFlow(signal: Signal, stack: Stack<Signal>): Flow? {
        return findWaitingSignal(signal, stack)?.waitingFlow

    }

    private fun findWaitingSignal(signal:Signal, stack: Stack<Signal>) : Signal? {
        for (item in stack) {
            if (item.waitingSignal == signal.startSignal) {
                println("Flow waiting signal\"$signal\" found: $item")
                return item
            }
        }
        return null
    }


    private fun printQueues() {
        println("Queues updated...")
        if (highFlowQueue.isNotEmpty()) println("\tHigh: $highFlowQueue")
        if (mediumFlowQueue.isNotEmpty()) println("\tMedium: $mediumFlowQueue")
        if (lowFlowQueue.isNotEmpty()) println("\tLow: $lowFlowQueue")
    }

    fun removeSignal(signal: Signal) {
        highFlowQueue.remove(signal)
        mediumFlowQueue.remove(signal)
        lowFlowQueue.remove(signal)

        println("Removed Signal: \n\t $signal")
        printQueues()

    }

}
