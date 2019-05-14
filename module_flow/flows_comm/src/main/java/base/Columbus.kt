package base

class Columbus {

    // Expose a method to add a signal-flow pair in the map
    // after adding, sort it based on priority

    private lateinit var router: Router

    fun registerRouter(router: Router) {
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

    fun route(routeEvent: RouteEvent) {
        router.route(routeEvent)
    }

    fun addRoutereventInMap(routeEvent: RouteEvent) {
        router.addRoute(routeEvent)
    }
}