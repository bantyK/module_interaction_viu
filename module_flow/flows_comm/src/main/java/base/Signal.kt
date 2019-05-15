package base


data class Signal(
    val startSignal: String,
    val waitingSignal: String?,
    val waitingFlow: Flow?
) {
    override fun toString(): String {
        return "[$startSignal, $waitingSignal, ${waitingFlow.toString()}]"
    }
}