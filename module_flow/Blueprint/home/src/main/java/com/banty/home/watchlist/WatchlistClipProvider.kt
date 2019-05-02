package com.banty.home.watchlist

import com.banty.core.model.Clip
import javax.inject.Inject

/**
 * Created by Banty on 2019-05-02.
 */

const val watchListThumb = "https://www.medianews4u.com/wp-content/uploads/2017/01/viu-and-vvclip-logo-2-2.jpg"
class WatchlistClipProvider @Inject constructor() {

    fun provideWatchlistClip(): List<Clip> {
        return listOf(
                Clip("1", "Watchlist Clip 1", false, watchListThumb),
                Clip("2", "Watchlist Clip 2", true, watchListThumb),
                Clip("3", "Watchlist Clip 3", false, watchListThumb)
        )
    }
}