package base

interface Router {
    fun route(routeEvent: RouteEvent)
    fun addRoute(routeEvent: RouteEvent)
}