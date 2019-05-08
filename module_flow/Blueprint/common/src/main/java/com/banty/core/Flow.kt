package com.banty.core

import android.content.Context
import com.banty.core.signal.Signal

/**
 * Created by Banty on 2019-05-06.
 *
 * Interface to be implemented by all the flows and blocks.
 */
interface Flow {
    fun start(context: Context, payload: HashMap<String, Any>)
    fun await(signal: Signal)
}