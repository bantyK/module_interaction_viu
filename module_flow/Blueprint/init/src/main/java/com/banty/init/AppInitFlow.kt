package com.banty.init

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.banty.core.Flow
import com.banty.init.app_init.SplashFragment

/**
 * Created by Banty on 2019-05-06.
 */
class AppInitFlow : Flow {
    override fun init(context: Context, payload: HashMap<String, Any>) {
        val fragmentContainer: Int = payload["fragment_container"] as Int

        (context as AppCompatActivity)
                .supportFragmentManager
                .beginTransaction()
                .replace(fragmentContainer, SplashFragment())
                .commit()
    }
}