package base

import java.util.*

class QueueManager {

    private val highSignalQueue = Stack<Signal>()

    private val mediumSignalQueue = Stack<Signal>()

    private val lowSignalQueue = Stack<Signal>()

    fun submitSignal(signal: Signal) {
        println("\n\nNew signal: \n\t$signal")
        when {
            signalPresentInWaitingQueues(signal) -> println("Signal found in waiting:\n\t$signal")

            signal.waitingFlow == null -> lowSignalQueue.add(signal)

            else -> addInWaitingQueue(signal.waitingFlow, signal)
        }

        printQueues()
    }

    private fun addInWaitingQueue(waitingFlow: Flow, signal: Signal) {
        when (waitingFlow.getPriorityLevel()) {
            FlowPriority.LOW -> lowSignalQueue.add(signal)
            FlowPriority.MEDIUM -> mediumSignalQueue.add(signal)
            FlowPriority.HIGH -> highSignalQueue.add(signal)
        }
    }

    fun signalPresentInWaitingQueues(signal: Signal): Boolean {
        return findWaitingSignal(signal, highSignalQueue) != null ||
                findWaitingSignal(signal, mediumSignalQueue) != null ||
                findWaitingSignal(signal, lowSignalQueue) != null
    }

    fun findWaitingFlow(signal: Signal): Flow? {
        println("Any flow waiting for \"${signal.startSignal}\"?")

        // first find a flow in low queue, if flow is not found then proceed to other queues
        var flow = findWaitingFlow(signal, lowSignalQueue)

        if (flow == null) {
            flow = findWaitingFlow(signal, mediumSignalQueue)
        }

        if (flow == null) {
            flow = findWaitingFlow(signal, highSignalQueue)
        }

        return flow
    }

    private fun findWaitingFlow(signal: Signal, stack: Stack<Signal>): Flow? {
        return findWaitingSignal(signal, stack)?.waitingFlow
    }

    fun findWaitingSignal(signal: Signal): Signal? {
        var waitingSignal = findWaitingSignal(signal, lowSignalQueue)

        if (waitingSignal == null) waitingSignal = findWaitingSignal(signal, mediumSignalQueue)

        if (waitingSignal == null) waitingSignal = findWaitingSignal(signal, highSignalQueue)

        println("Waiting signal found\n\t$waitingSignal")
        return waitingSignal
    }

    /*
    * Finds if there is any flow which is waiting for the received signal
    * */
    private fun findWaitingSignal(signal: Signal, stack: Stack<Signal>): Signal? {
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
        if (highSignalQueue.isNotEmpty()) println("\tHigh: $highSignalQueue")
        if (mediumSignalQueue.isNotEmpty()) println("\tMedium: $mediumSignalQueue")
        if (lowSignalQueue.isNotEmpty()) println("\tLow: $lowSignalQueue")
    }

    fun removeSignal(signal: Signal) {
        highSignalQueue.remove(signal)
        mediumSignalQueue.remove(signal)
        lowSignalQueue.remove(signal)

        println("Removed Signal: \n\t $signal")
        printQueues()
    }

    fun getPrioritySignal(currentSignal: Signal): Signal? {
        //check if some flow is waiting, if yes - process signal right away

        var signal = getSignalFromQueue(highSignalQueue)

        if (signal == null) signal = getSignalFromQueue(mediumSignalQueue)

        if (signal == null) signal = getSignalFromQueue(lowSignalQueue)

        return signal
    }

    private fun getSignalFromQueue(queue: Stack<Signal>): Signal? =
        if (queue.isNotEmpty()) queue.peek() else null

    fun addSignal() {

    }

}
