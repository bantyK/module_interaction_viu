package deeplink

import base.Columbus
import base.Signal

class DeeplinkListener {

    fun emitDeeplinkSignal() {
        println("Deeplink callback received..")
        Columbus.getColumbus().submit(
            Signal(
                "CLIP_DETAIL",
                null,
                null
            )
        )
    }

}