import base.*

fun main() {

    val mainRouter = MainRouter(
        FlowManager(),
        FlowSignalMapper()
    )

    Columbus.getColumbus().registerRouter(mainRouter)

    Columbus.getColumbus().route(
        RouteEvent(
            "SHOW_HOME",
            null,
            null
        )
    )

    // infinite loop to keep the app alive
    while (true) {
    }
}