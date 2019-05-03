package com.banty.blueprintapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.banty.columbus.Columbus
import com.banty.core.Flow
import com.banty.core.model.Clip
import com.banty.home.HomeFragment
import com.banty.init.app_init.SplashFragment
import com.banty.player.PlayerFragment

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

    override fun navigateTo(flow: Flow, payload: HashMap<String, Any>) {
        when (flow.getName()) {
            "App init flow" ->
                addFragment(SplashFragment(), true)

            "HomeFlow" ->
                addFragment(HomeFragment(), true)

            "Player flow" -> {
                addFragment(PlayerFragment.getInstance(payload["clip"] as Clip), false)
            }
        }

    }

    private fun addFragment(fragment: Fragment, replace:Boolean) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        if(replace) {
            fragmentTransaction
                    .replace(R.id.fragment_container, fragment)
                    .commit()
        } else {
            fragmentTransaction
                    .add(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit()
        }

        supportActionBar?.title = (fragment as Flow).getName()
    }

    override fun onStop() {
        super.onStop()
        mainPresenter.unregister()
    }
}
