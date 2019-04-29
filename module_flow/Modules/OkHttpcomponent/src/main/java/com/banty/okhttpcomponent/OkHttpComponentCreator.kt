package com.banty.okhttpcomponent

import com.banty.httpinterface.IHttpInterface
import com.banty.moduleinterface.ImplCreator

/**
 * Created by Banty on 2019-04-29.
 */
class OkHttpComponentCreator : ImplCreator<IHttpInterface> {
    override fun createImpl(): IHttpInterface {
        return OkHttpComponent()
    }
}