package com.vuclip.viu2.init_feature

import com.vuclip.viu2.base.Invokable
import com.vuclip.viu2.device.Device
import com.vuclip.viu2.gps.GpsLocation
import com.vuclip.viu2.location.Location
import com.vuclip.viu2.programming.Programming

class SignalModuleMapper {
    fun getModule(componentId: String) : Invokable? {
        when(componentId) {
            "init_device" -> return Device()
            "location" -> return Location()
            "gps_location" -> return GpsLocation()
            "network" -> return Programming()
        }

        return null
    }

}
