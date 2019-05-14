package base

class FlowManager {

    private val flowSignalArrayList = arrayListOf<FlowSignalMap>()


    fun addFlow(signal: String, flow: Flow) {
        flowSignalArrayList.add(FlowSignalMap(signal, flow))
    }

    fun isFlowWaitingForSignal(signal: String): Boolean {
        for (item in flowSignalArrayList) {
            if (item.signal == signal) {
                return true
            }
        }
        return false
    }

    fun getFlow(signal: String): Flow? {
        for (item in flowSignalArrayList) {
            if (item.signal == signal) {
                return item.flow
            }
        }

        return null
    }

}

private class FlowSignalMap(
    val signal: String,
    val flow: Flow
)
