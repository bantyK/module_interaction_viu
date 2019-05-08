package com.banty.auth

import android.content.Context
import com.banty.core.Flow
import com.banty.core.signal.Signal
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by Banty on 2019-05-06.
 */
class AuthBlock : Flow{
    override fun await(signal: Signal) {

    }

    override fun start(context: Context, payload: HashMap<String, Any>) {
        GlobalScope.launch {
            AuthService().startUserAuthentication(context, payload)
        }

    }
}