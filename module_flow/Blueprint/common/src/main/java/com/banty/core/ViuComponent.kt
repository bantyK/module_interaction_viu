package com.banty.core

import android.content.Context

/**
 * Created by Banty on 2019-05-06.
 *
 * Interface to be implemented by all the flows and blocks.
 */
interface ViuComponent {

    fun init(context: Context, payload: HashMap<String, Any> = HashMap())
}