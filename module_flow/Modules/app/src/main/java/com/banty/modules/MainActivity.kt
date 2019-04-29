package com.banty.modules

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.banty.analyticsinterface.IAnalytics
import com.banty.httpinterface.IHttpInterface
import com.banty.moduleinterface.ModuleManager
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        send_event.setOnClickListener {
            callAnalytics()
        }


        http.setOnClickListener {
            doHttpOperation()
        }
    }

    private fun doHttpOperation() {
        val iHttpInterface = ModuleManager.getInstance().getModule(IHttpInterface::class.java.simpleName)
            .getImpl(true) as IHttpInterface

        iHttpInterface.get("http://www.viu.com")
        iHttpInterface.post("http://www.viu.com", JSONObject().put("key1", "value1"))
    }

    private fun callAnalytics() {
        val iAnalytics =
            ModuleManager.getInstance().getModule(IAnalytics::class.java.simpleName).getImpl(true) as IAnalytics
        iAnalytics.identify(JSONObject().put("user_country", "india"))
        iAnalytics.trackEvent("app_launch", JSONObject().put("platform", "viu").put("ccode", "in"))
    }
}
