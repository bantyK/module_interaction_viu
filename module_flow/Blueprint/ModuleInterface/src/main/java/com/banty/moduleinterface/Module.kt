package com.banty.moduleinterface

/**
 * Created by Banty on 2019-05-03.
 */
class Module<T> {

    private var mImplCreator: ImplCreator<T>? = null

    private var mImpl: T? = null

    fun setImplCreator(implCreator: ImplCreator<T>) {
        if (mImplCreator == null) {
            mImplCreator = implCreator
        }
    }

    fun getImplCreator() = mImplCreator

    fun createImpl(): T {
        return mImplCreator?.createImpl() as T
    }

    fun getImpl(createIfNExist: Boolean): T {
        if (createIfNExist && mImpl == null) {
            mImpl = createImpl()
        }
        return mImpl!!
    }
}