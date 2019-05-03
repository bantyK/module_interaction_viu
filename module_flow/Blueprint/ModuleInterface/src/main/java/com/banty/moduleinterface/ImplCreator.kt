package com.banty.moduleinterface

/**
 * Created by Banty on 2019-05-03.
 */
interface ImplCreator<T> {

    fun createImpl(): T
}
