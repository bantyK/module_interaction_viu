package com.vuclip.viu2.init_feature

import com.vuclip.viu2.base.Invokable
import com.vuclip.viu2.device.Device
import com.vuclip.viu2.gps.GpsLocation
import com.vuclip.viu2.location.Location
import com.vuclip.viu2.network.NetworkComponent
import com.vuclip.viu2.programming.ProgrammingComponent
import com.vuclip.viu2.security.SecurityComponent
import com.vuclip.viu2.user.UserComponent
import com.vuclip.viu2.viu_security.ViuSecurity

// todo : This will be dynamic

class SignalModuleMapper {
    fun getModule(componentId: String) : Invokable? {
        when(componentId) {
            "init_device" -> return Device()
            "location" -> return Location()
            "gps_location" -> return GpsLocation()
            "network" -> return NetworkComponent()
            "security" -> return SecurityComponent()
            "user" -> return UserComponent()
            "programming" -> return ProgrammingComponent()
            "viu_security" -> return ViuSecurity()
        }

        return null
    }

}
