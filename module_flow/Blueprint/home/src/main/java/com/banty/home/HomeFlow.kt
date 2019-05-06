package com.banty.home

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.banty.core.ViuComponent
import java.lang.IllegalStateException

/**
 * Created by Banty on 2019-05-06.
 */
class HomeFlow : ViuComponent {

    override fun init(context: Context, payload: HashMap<String, Any>) {

        if(payload.containsKey("fragment_container")) {
            val fragmentContainer = payload["fragment_container"] as Int
            (context as AppCompatActivity)
                    .supportFragmentManager
                    .beginTransaction()
                    .replace(fragmentContainer, HomeFragment())
                    .commit()
        } else {
            throw IllegalStateException("Mandatory dependency is missing in payload")
        }
    }
}