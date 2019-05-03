package com.banty.blueprintapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.banty.columbus.Columbus
import com.banty.core.Flow
import com.banty.home.HomeFragment
import com.banty.init.app_init.SplashFragment

class MainActivity : AppCompatActivity(), MainActivityView {

    private lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainPresenter = MainPresenter(this, Columbus.getColumbus())
    }

    override fun onResume() {
        super.onResume()
        mainPresenter.register()
        mainPresenter.startInit()
    }

    override fun navigateTo(flow: Flow) {
        when (flow.getName()) {
            "App init flow" ->
                addFragment(SplashFragment())

            "HomeFlow" ->
                addFragment(HomeFragment())
        }

    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()

        supportActionBar?.title = (fragment as Flow).getName()
    }

    override fun onStop() {
        super.onStop()
        mainPresenter.unregister()
    }
}
