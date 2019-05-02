package com.banty.init.location

import javax.inject.Inject

/**
 * Created by Banty on 2019-05-02.
 */
class LocationService @Inject constructor() {

    fun getLocation(): String {
        return "in"
    }
}