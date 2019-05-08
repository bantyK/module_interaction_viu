package com.banty.player

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.banty.columbus.Columbus
import com.banty.core.Flow
import com.banty.core.model.Clip
import com.banty.core.signal.Signal
import com.banty.player.fragment.PlayerFragment

class PlayerFlow : Flow {

    private lateinit var playerFragment: PlayerFragment

    override fun start(context: Context, payload: HashMap<String, Any>) {
        Log.d("Viu", "Player flow start")
        val fragmentContainer: Int = payload["fragment_container"] as Int
        val clip = payload["clip"] as Clip?
        val subStatus = payload["sub_status"] as String?

        playerFragment = PlayerFragment.getInstance(clip, subStatus, this)
        (context as AppCompatActivity)
            .supportFragmentManager
            .beginTransaction()
            .replace(fragmentContainer, playerFragment)
            .addToBackStack(null)
            .commit()

    }

    override fun await(signal: Signal) {
        if (subscriptionSuccessful(signal))
            playerFragment.resumePlayback()
    }

    private fun subscriptionSuccessful(signal: Signal) =
        signal == Signal.SUBS_STATUS

    fun registerWaiting(nextSignal: Signal, payload: HashMap<String, Any>, waitingSignal: Signal) {
        Columbus.getColumbus().postEvent(nextSignal, payload, waitingSignal, this)
    }
}