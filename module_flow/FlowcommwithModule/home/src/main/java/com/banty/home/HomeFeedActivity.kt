package com.banty.home

import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.banty.base.ViuEventBus
import com.banty.base.signals.Signal
import com.banty.base.signals.SignalType
import com.banty.base.signals.ViuSignal
import kotlinx.android.synthetic.main.activity_home_feed.*

const val TAG = "HomeFeedActivity"

internal class HomeFeedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_feed)
    }

    override fun onStart() {
        super.onStart()
        startUI(intent?.getStringArrayListExtra("content"))
    }

    private fun startUI(contentList: ArrayList<String>?) {
        contentList?.let { list ->

            val adapter = ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_list_item_1,
                    list)
            content_list.adapter = adapter

            content_list.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
                val payload = HashMap<String, Any>()
                payload["cid"] = list[position]

                ViuEventBus.getInstance().postEvent(
                        Signal(
                                ViuSignal.CONTENT_DETAIL,
                                SignalType.INTER_FLOW,
                                HomeFeedActivity::class.java.simpleName,
                                payload,
                                this@HomeFeedActivity
                        )
                )
            }

        } ?: Log.e(TAG, "No content item passed in intent")
    }
}
