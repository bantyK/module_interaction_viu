package com.banty.blueprintapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.banty.columbus.Columbus
import com.banty.core.Flow

class MainActivity : AppCompatActivity(), MainActivityView {

    private lateinit var mMainRouter: MainRouter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mMainRouter = MainRouter(this,
                Columbus.getColumbus(),
                (application as App).getObjectMapper())
    }

    override fun onResume() {
        super.onResume()
        mMainRouter.register()
        mMainRouter.startInit(R.id.fragment_container)
    }

    override fun navigateTo(module: Flow, payload: HashMap<String, Any>) {
        payload["fragment_container"] = R.id.fragment_container

        module.start(this, payload)
    }

    override fun onStop() {
        super.onStop()
        mMainRouter.unregister()
    }
}
