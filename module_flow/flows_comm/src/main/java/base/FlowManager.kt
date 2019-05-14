package base

class FlowManager {
    private val flowSignalMap = HashMap<String, Flow>()

    fun addFlow(waitingSignal: String, flow: Flow) {
        flowSignalMap.put(waitingSignal, flow)
    }

    fun isFlowWaitingForSignal(signal: String): Boolean {
        return flowSignalMap.containsKey(signal)
    }

    fun getFlow(signal: String): Flow {
        return flowSignalMap[signal]!!
    }


}
