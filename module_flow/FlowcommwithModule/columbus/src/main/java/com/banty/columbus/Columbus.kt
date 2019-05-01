package com.banty.columbus

import com.banty.base.ViuEventBus
import com.banty.base.signals.Signal
import com.banty.base.signals.ViuSignal
import com.banty.detail.DetailFlow
import com.banty.home.HomeFeed
import org.greenrobot.eventbus.Subscribe

/**
 * Created by Banty on 2019-04-30.
 */
const val TAG = "Columbus"

class Columbus {

    companion object {
        val INSTANCE = Columbus()

        fun getInstance() = INSTANCE
    }

    fun initColumbus() {
        ViuEventBus.getInstance().subscribe(this)
    }

    @Subscribe
    fun registerEvent(signal: Signal) {
        when (signal.name) {
            ViuSignal.GET_FEED -> {
                HomeFeed().showHomePage(signal.context)
            }

            ViuSignal.CONTENT_DETAIL -> {
                DetailFlow().showDetail(signal.payload, signal.context)
            }

            ViuSignal.CLIP_ID_MISSING -> {
                HomeFeed().getDifferentProgramming()
            }
        }
    }
}