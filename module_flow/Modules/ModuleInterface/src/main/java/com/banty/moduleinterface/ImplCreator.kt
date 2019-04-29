package com.banty.moduleinterface

/**
 * Created by Banty on 26/04/19.
 */
interface ImplCreator<T> {

    fun createImpl(): T
}
