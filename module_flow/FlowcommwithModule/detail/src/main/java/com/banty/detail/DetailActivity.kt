package com.banty.detail

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.banty.base.ViuEventBus
import com.banty.base.signals.Signal
import com.banty.base.signals.SignalType
import com.banty.base.signals.ViuSignal
import kotlinx.android.synthetic.main.activity_detail.*

internal class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val cid = intent?.getStringExtra("cid")

        if (cid != null) {
            Log.d("TAG", "Data from intent $cid")
            textView_detail.text = cid
        } else {
            ViuEventBus.getInstance().postEvent(
                    Signal(
                            ViuSignal.CLIP_ID_MISSING,
                            SignalType.INTER_FLOW,
                            DetailActivity::class.java.simpleName
                    )
            )
        }
    }
}
