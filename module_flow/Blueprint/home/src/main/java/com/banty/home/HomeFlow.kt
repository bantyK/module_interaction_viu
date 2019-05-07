package com.banty.home

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.banty.core.Flow
import com.banty.core.signal.Signal

/**
 * Created by Banty on 2019-05-06.
 */
class HomeFlow : Flow {
    override fun await(signal: Signal) {

    }

    override fun init(context: Context, payload: HashMap<String, Any>) {

        if (payload.containsKey("fragment_container")) {
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