package com.banty.home.recent

import com.banty.core.model.Clip
import javax.inject.Inject

/**
 * Created by Banty on 2019-05-02.
 */
const val recentThumb = "https://images-eu.ssl-images-amazon.com/images/I/31eLGiTP5IL.png"
class RecentWatchedClipProvider @Inject constructor() {

    fun provideRecentlyWatchedClip(): List<Clip> {
        return listOf(
                Clip("1", "Recent Clip 1", false, recentThumb),
                Clip("2", "Recent Clip 2", false, recentThumb),
                Clip("3", "Recent Clip 3", true, recentThumb)
        )
    }
}