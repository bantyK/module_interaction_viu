package com.banty.blueprintapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.banty.columbus.Columbus
import com.banty.core.ViuComponent

class MainActivity : AppCompatActivity(), MainActivityView {

    private lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainPresenter = MainPresenter(this,
                Columbus.getColumbus(),
                (application as App).getObjectMapper())
    }

    override fun onResume() {
        super.onResume()
        mainPresenter.register()
        mainPresenter.startInit(R.id.fragment_container)
    }

    override fun navigateTo(module: ViuComponent, payload: HashMap<String, Any>) {
        payload["fragment_container"] = R.id.fragment_container
        module.init(this, payload)
    }

    override fun onStop() {
        super.onStop()
        mainPresenter.unregister()
    }
}
