package com.banty.home.ui

import com.banty.core.model.Clip

interface IHomePresenter {

    fun getRecentlyWatchClips(): List<Clip>


    interface HomeView {

        fun configureHomeFeed()

        fun showWatchlist()

        fun hideWatchlist()

        fun showRecentlyWatch()

        fun hideRecentlyWatch()
    }

    fun provideWatchListClips(): List<Clip>
    fun getHomeFeed(): List<Clip>
    fun setupHomePage()

    fun sendClipClickedEvent(clip: Clip)
}
