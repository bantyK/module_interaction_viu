package com.banty.base.signals

import android.content.Context

/**
 * Created by Banty on 2019-04-30.
 *
 * A signal which can also carry a context
 */
class Signal(
        val name:ViuSignal,
        val type: SignalType,
        val source: String,
        val payload: HashMap<String, Any> = HashMap(),
        val context: Context? = null)

enum class SignalType {
    INTRA_FLOW,
    INTER_FLOW
}

enum class ViuSignal {
    GET_FEED,

    CONTENT_DETAIL,

    CLIP_ID_MISSING
}