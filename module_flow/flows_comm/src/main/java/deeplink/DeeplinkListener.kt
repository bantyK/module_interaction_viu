package deeplink

import base.Columbus
import base.Signal

class DeeplinkListener {

    fun emitDeeplinkSignal() {
        Columbus.getColumbus().submit(
            Signal(
                "CLIP_DETAIL",
                null,
                null
            )
        )
    }

}