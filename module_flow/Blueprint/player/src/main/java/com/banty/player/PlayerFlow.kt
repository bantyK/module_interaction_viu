package com.banty.player

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.banty.core.Flow
import com.banty.core.model.Clip
import com.banty.core.signal.Signal
import com.banty.player.fragment.PlayerFragment

class PlayerFlow : Flow {

    private lateinit var playerFragment: PlayerFragment

    override fun init(context: Context, payload: HashMap<String, Any>) {
        val fragmentContainer: Int = payload["fragment_container"] as Int
        val clip = payload["clip"] as Clip?
        val subStatus = payload["sub_status"] as String?

        playerFragment = PlayerFragment.getInstance(clip, subStatus)
        (context as AppCompatActivity)
                .supportFragmentManager
                .beginTransaction()
                .add(fragmentContainer, playerFragment)
                .addToBackStack(null)
                .commit()

    }

    override fun await(signal: Signal) {
        if (subscriptionSuccessful(signal))
            playerFragment.resumePlayback()
    }

    private fun subscriptionSuccessful(signal: Signal) =
            signal == Signal.SUBS_STATUS
}