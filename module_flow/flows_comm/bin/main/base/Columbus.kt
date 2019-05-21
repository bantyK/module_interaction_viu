package base

class Columbus() {

    private lateinit var router: IRouter

    private val queueManager = QueueManager()

    private val signalResolver = SignalResolver(queueManager)

    fun registerRouter(router: IRouter) {
        this.router = router
    }

    companion object {
        private var INSTANCE: Columbus? = null

        fun getColumbus(): Columbus {
            synchronized(Columbus::class) {
                if (INSTANCE == null) {
                    INSTANCE = Columbus()
                }
            }
            return INSTANCE!!
        }
    }

    fun submit(signal: Signal) {
        queueManager.submitSignal(signal)
        val flow = signalResolver.handle(signal)
        router.submit(flow)
    }
}