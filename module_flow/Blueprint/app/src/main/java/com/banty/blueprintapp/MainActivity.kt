package com.banty.blueprintapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.banty.home.HomeFragment
import com.banty.init.app_init.SplashFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, HomeFragment())
                .commit()

        supportActionBar?.title = "App init flow - Splash fragment"
    }
}
