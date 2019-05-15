package deeplink

import base.Columbus
import base.Signal
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DeeplinkListener {

    fun emitDeeplinkSignal() {
        GlobalScope.launch {
            delay(1_000)

            Columbus.getColumbus().submit(
                Signal(
                    "CLIP_DETAIL",
                    null,
                    null
                )
            )
        }
    }
}