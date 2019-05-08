package com.banty.player.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.banty.core.model.Clip
import com.banty.core.signal.Signal
import com.banty.player.PlayerFlow
import com.banty.player.R
import kotlinx.android.synthetic.main.fragment_player.*

@SuppressLint("SetTextI18n")
class PlayerFragment(private val flow: PlayerFlow) : Fragment() {

    private var clip: Clip? = null

    companion object {
        fun getInstance(clip: Clip?, subStatus: String?, flow: PlayerFlow): PlayerFragment {
            val bundle = Bundle()
            bundle.putParcelable("clip", clip)
            bundle.putString("sub_status", subStatus)
            val fragment = PlayerFragment(flow)
            fragment.arguments = bundle
            return fragment
        }

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d("Viu", "Player view created")
        val view = inflater.inflate(R.layout.fragment_player, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clip = arguments?.get("clip") as Clip?
        val subStatus = arguments?.get("sub_status") as String?
        if (clipIsValidAndUserIsNotBasic(clip, subStatus)) {
            textView_player.text = "Subscription required"
            button_subscription.visibility = View.VISIBLE
        } else {
            resumePlayback()
        }

        button_subscription.setOnClickListener {
            val payload = HashMap<String, Any>()
            payload["clip"] = clip!!
            flow.registerWaiting(Signal.SUBS_REQ, payload, Signal.SUBS_STATUS)
        }
    }

    private fun clipIsValidAndUserIsNotBasic(clip: Clip?, subStatus: String?) =
            clip != null && clip.isPaid && TextUtils.equals(subStatus, "BASIC")


    fun resumePlayback() {
        textView_player.text = "Playing clip :${clip?.title}"
        button_subscription.visibility = View.GONE
    }
}