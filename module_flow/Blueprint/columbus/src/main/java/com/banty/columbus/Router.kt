package com.banty.columbus

import com.banty.core.Flow
import com.banty.core.signal.Signal

/**
 * Created by Banty on 2019-05-03.
 */
interface Router {
    fun navigateTo(signal: Signal, payload: HashMap<String, Any>, awaitSignal: Signal?, waitingFlow: Flow?)
}