package com.banty.init.di

import com.banty.init.location.LocationService
import com.banty.init.programming.Programming
import com.banty.init.security.SecurityService
import dagger.Component

/**
 * Created by Banty on 2019-05-02.
 */
@Component
interface AppInitComponent {

    fun injectLocation() : LocationService

    fun injectProgramming() : Programming

    fun injectSecurityService() : SecurityService
}