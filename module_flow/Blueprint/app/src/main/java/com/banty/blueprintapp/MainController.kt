package com.banty.blueprintapp

/**
 * Created by Banty on 2019-05-03.
 */
interface MainController : Controller {
    fun navigateTo(screen:Screen)
}

sealed class Screen {
    object HomeFlow : Screen()
    object AppInitFlow : Screen()
}