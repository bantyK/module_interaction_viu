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
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * Created by Banty on 2019-05-02.
 */
class SplashFragment : Fragment(), AppInitStateMachine, Flow, CoroutineScope {
    val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main


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
        launch {
            async {
                presenter.stateAppInit()
            }
        }


        button_home.setOnClickListener {
            presenter.sendAppInitSuccessSignal()
        }
    }

    @SuppressLint("SetTextI18n")
    override fun stateChanged(state: AppInitStates, status: Status) {
        message = "Block $state, status $status \n"
        Log.d("Viu", message)

        if (status == Status.FAILED) {
            if (state == AppInitStates.CHECK_APP_UPGRADE)
            // send APP_UPGRADE_REQ status to columbus and halt the app init
                return
            else if (state == AppInitStates.CHECK_NETWORK) {
                textView_splash.text = textView_splash.text.toString() + message
                stateChanged(AppInitStates.PROGRAMMING, Status.SUCCESS)
            }
        } else {
            when (state) {
                AppInitStates.CHECK_NETWORK -> {
                    launch {
                        async {
                            presenter.checkAppUpgrade()
                        }
                        textView_splash.text = textView_splash.text.toString() + message
                    }
                }

                AppInitStates.CHECK_APP_UPGRADE -> {
                    launch {
                        async {
                            presenter.checkLocation()
                        }
                        textView_splash.text = textView_splash.text.toString() + message
                    }
                }

                AppInitStates.LOCATION -> {
                    launch {
                        async {
                            presenter.getSecurity()
                        }
                        textView_splash.text = textView_splash.text.toString() + message
                    }
                }

                AppInitStates.SECURITY -> {
                    launch {
                        async {
                            presenter.getProgramming()
                        }
                        textView_splash.text = textView_splash.text.toString() + message
                    }
                }

                AppInitStates.PROGRAMMING -> {
                    button_home.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Viu", "App init flow destroyed")
    }
}