package com.banty.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.banty.core.Flow
import com.banty.core.model.Clip
import kotlinx.android.synthetic.main.layout_player.*

/**
 * Created by Banty on 2019-05-03.
 */
class PlayerFragment : Fragment(), Flow {

    override fun getName(): String {
        return "Player flow"
    }

    companion object {

        fun getInstance(clip: Clip): PlayerFragment {
            val bundle = Bundle()
            val playerFragment = PlayerFragment()
            bundle.putParcelable("clip", clip)
            playerFragment.arguments = bundle
            return playerFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_player, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView_clip_info.text = arguments?.getParcelable<Clip>("clip")?.title
    }
}