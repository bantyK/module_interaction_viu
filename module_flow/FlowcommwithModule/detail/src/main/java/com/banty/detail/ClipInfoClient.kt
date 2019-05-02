package com.banty.detail

/**
 * Created by Banty on 2019-05-01.
 */
class ClipInfoClient {

    fun getClipData(cid: String): Clip {
        return Clip(cid, title = "Memories", isPaid = false)
    }
}

data class Clip(
        val cid: String,
        val title: String,
        val isPaid: Boolean
)