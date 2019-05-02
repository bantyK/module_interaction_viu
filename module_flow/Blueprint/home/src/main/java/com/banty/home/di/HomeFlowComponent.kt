package com.banty.home.di

import com.banty.config.HomeFlowConfig
import com.banty.core.graphql.GraphqlClient
import com.banty.home.recent.RecentWatchedClipProvider
import com.banty.home.watchlist.WatchlistClipProvider
import dagger.Component

/**
 * Created by Banty on 2019-05-02.
 */
@Component
interface HomeFlowComponent {

    fun provideHomeFlowConfig() : HomeFlowConfig

    fun provideWatchlistProvider() : WatchlistClipProvider

    fun provideRecentlyWatchProvider() : RecentWatchedClipProvider

    fun provideGraphQlClient() : GraphqlClient
}