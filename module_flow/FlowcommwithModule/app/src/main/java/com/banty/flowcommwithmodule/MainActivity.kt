package com.banty.flowcommwithmodule

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.banty.base.ViuEventBus
import com.banty.base.signals.Signal
import com.banty.base.signals.SignalType
import com.banty.base.signals.ViuSignal

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val payload = HashMap<String, Any>()
        payload["key1"] = "value"
        payload["key2"] = 1
        
        ViuEventBus.getInstance().postEvent(
                Signal(ViuSignal.GET_FEED, SignalType.INTER_FLOW, MainActivity::class.java.simpleName, payload, this)
        )
    }
}
