package com.vuclip.viu2.init_feature

import com.vuclip.viu2.base.BaseComponent
import com.vuclip.viu2.device.DeviceComponent
import com.vuclip.viu2.gps.GpsLocationComponent
import com.vuclip.viu2.location.LocationComponent

class SignalModuleMapper {
    fun getModule(componentId: String) : BaseComponent? {
        when(componentId) {
            "init_device" -> return DeviceComponent()
            "location" -> return LocationComponent()
            "gps_location" -> return GpsLocationComponent()
        }

        return null
    }

}
