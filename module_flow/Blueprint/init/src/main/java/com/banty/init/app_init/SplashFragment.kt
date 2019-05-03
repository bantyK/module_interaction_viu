package com.banty.init.app_init

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.banty.core.Flow
import com.banty.init.R
import kotlinx.android.synthetic.main.splash_fragment.*

/**
 * Created by Banty on 2019-05-02.
 */
class SplashFragment : Fragment(), AppInitStateMachine, Flow {

    var message = ""

    override fun getName(): String {
        return "App init flow"
    }

    private lateinit var presenter: SplashFragmentPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d("Viu", "App init flow started")
        return inflater.inflate(R.layout.splash_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = SplashFragmentPresenter(this)
        presenter.startInit()
    }

    @SuppressLint("SetTextI18n")
    override fun stateChanged(state: AppInitStates, status: Status) {
        message = "App init state $state with Status $status \n"
        Log.d("Viu", message)
        textView_splash.text = message

        if (state == AppInitStates.CHECK_APP_UPGRADE && status == Status.FAILED) {
            // send APP_UPGRADE_REQ status to columbus and halt the app init
            return
        }

        when (state) {
            AppInitStates.CHECK_APP_UPGRADE -> {
                presenter.checkLocation()
            }

            AppInitStates.LOCATION -> {
                presenter.getSecurity()
            }

            AppInitStates.SECURITY -> {
                presenter.getProgramming()
            }

            AppInitStates.PROGRAMMING -> {
                presenter.sendAppInitSuccessSignal()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Viu", "App init flow destroyed")
    }
}