import base.Columbus
import base.NavigationManager
import base.Signal
import deeplink.DeeplinkListener

fun main() {

    val mainRouter = NavigationManager()

    Columbus.getColumbus().registerRouter(mainRouter)

    Thread(Runnable {
        Thread.sleep(6_000)
        DeeplinkListener().emitDeeplinkSignal()
    }).start()

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