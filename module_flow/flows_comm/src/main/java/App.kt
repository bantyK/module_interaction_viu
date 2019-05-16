import base.*
import deeplink.DeeplinkListener

fun main() {

    val mainRouter = NavigationManager()

    Columbus.getColumbus().registerRouter(mainRouter)

//    DeeplinkListener().emitDeeplinkSignal()
    Columbus.getColumbus().submit(
        Signal(
            "SHOW_HOME",
            null,
            null
        )
    )

    // infinite loop to keep the app alive
    while (true) {
    }
}