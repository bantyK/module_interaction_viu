package base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class Flow : CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Job()

    abstract fun start(context:String)
}
