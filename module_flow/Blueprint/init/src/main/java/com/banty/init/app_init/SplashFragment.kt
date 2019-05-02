package com.banty.init.app_init

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.banty.config.AppInitConfig
import com.banty.init.R
import kotlinx.android.synthetic.main.splash_fragment.*

/**
 * Created by Banty on 2019-05-02.
 */
class SplashFragment : Fragment(), AppInitStateMachine {

    private lateinit var appInitializer: AppInitializer

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.splash_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.actionBar?.title = SplashFragment::class.java.simpleName

        appInitializer = AppInitializer(AppInitConfig(), this)
        appInitializer.startInit()
    }

    @SuppressLint("SetTextI18n")
    override fun stateChanged(state: InitStates, status: Status) {

        textView_splash.append("App init state $state with Status $status \n")

        when (state) {
            InitStates.LOCATION -> {
                appInitializer.getSecurity()
            }

            InitStates.SECURITY -> {
                appInitializer.getProgramming()
            }

            InitStates.PROGRAMMING -> {
                // send event to Columbus about APP_INIT_PASS
            }
        }
    }
}