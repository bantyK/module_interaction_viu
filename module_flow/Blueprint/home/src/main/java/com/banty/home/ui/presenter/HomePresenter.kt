package com.banty.home.ui.presenter

import com.banty.columbus.Columbus
import com.banty.core.model.Clip
import com.banty.core.signal.Signal
import com.banty.home.di.DaggerHomeFlowComponent
import com.banty.home.ui.IHomePresenter

class HomePresenter(val view: IHomePresenter.HomeView?) : IHomePresenter {

    val homeFlowComponent = DaggerHomeFlowComponent.builder().build()

    val graphqlClient = homeFlowComponent.provideGraphQlClient()

    val watchedClipProvider = homeFlowComponent.provideWatchlistProvider()

    val recentWatchedClipProvider = homeFlowComponent.provideRecentlyWatchProvider()

    val homeFlowConfig = homeFlowComponent.provideHomeFlowConfig()

    override fun getHomeFeed(): List<Clip> {
        return graphqlClient.provideHomeFeed()
    }

    override fun getRecentlyWatchClips(): List<Clip> {
        return recentWatchedClipProvider.provideRecentlyWatchedClip()
    }

    override fun provideWatchListClips(): List<Clip> {
        return watchedClipProvider.provideWatchlistClip()
    }

    override fun setupHomePage() {
        view?.configureHomeFeed()

        if (homeFlowConfig.getHomeFlowConfig().optBoolean("home_flow_show_recently_watched")) {
            view?.showRecentlyWatch()
        } else {
            view?.hideRecentlyWatch()
        }

        if (homeFlowConfig.getHomeFlowConfig().optBoolean("home_flow_show_watchlist")) {
            view?.showWatchlist()
        } else {
            view?.hideWatchlist()
        }
    }

    override fun handleClipClickAction(clip: Clip) {
        val payload = HashMap<String, Any>()
        payload["clip"] = clip
        payload["sub_status"] = "BASIC"
        Columbus.getColumbus().postEvent(Signal.PLAY, payload, Signal.PLAY_SUCCESS)
    }

}
