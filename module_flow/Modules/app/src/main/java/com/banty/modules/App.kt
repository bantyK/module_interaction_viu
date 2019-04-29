package com.banty.modules

import android.app.Application
import android.content.pm.PackageManager
import android.util.Log
import com.banty.analyticsinterface.IAnalytics
import com.banty.httpinterface.IHttpInterface
import com.banty.moduleinterface.ImplCreator
import com.banty.moduleinterface.Module
import com.banty.moduleinterface.ModuleManager

/**
 * Created by Banty on 26/04/19.
 */
const val VIU_PLUGIN_DATA_SEPARATOR = "@"

const val TAG = "ModuleApp"

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        initAllModules()
    }

    private fun initAllModules() {
        try {
            val applicationInfo = packageManager
                .getApplicationInfo(packageName, PackageManager.GET_META_DATA)

            val bundle = applicationInfo.metaData

            for (key in bundle.keySet()) {
                if (key.startsWith("VIUAPP")) {
                    val pluginName = key.substring(key.indexOf(VIU_PLUGIN_DATA_SEPARATOR) + 1)

                    val keyValue = bundle.getString(key)
                    if (keyValue == null) {
                        Log.e(TAG, "Null value received for key: $key")
                        continue
                    }
                    val interfaceName = keyValue.substring(0, keyValue.indexOf(VIU_PLUGIN_DATA_SEPARATOR))
                    val implCreatorName = keyValue.substring(keyValue.indexOf(VIU_PLUGIN_DATA_SEPARATOR) + 1)
                    Log.d(
                        TAG, "pluginName:" + pluginName +
                                " interfaceName:" + interfaceName +
                                " implCreatorName:" + implCreatorName
                    )

                    val className = Class.forName(implCreatorName)

                    if (com.banty.moduleinterface.ModuleManager.getInstance().getModule(interfaceName) == null) {
                        setModuleForInterface(interfaceName)
                    }

                    ModuleManager.getInstance()
                        .getModule(interfaceName)
                        ?.setImplCreator(className.newInstance() as ImplCreator<*>)
                }
            }

        } catch (ex: PackageManager.NameNotFoundException) {
            Log.e(TAG, "PackageManager.NameNotFoundException during plugin initialization", ex);
        } catch (ex: ClassNotFoundException) {
            Log.e(TAG, "ClassNotFoundException during plugin initialization", ex);
        } catch (ex: InstantiationException) {
            Log.e(TAG, "InstantiationException during plugin initialization", ex);
        } catch (ex: IllegalAccessException) {
            Log.e(TAG, "IllegalAccessException during plugin initialization", ex);
        }
    }

    private fun setModuleForInterface(interfaceName: String) {
        when (interfaceName) {
            IAnalytics::class.java.simpleName -> {
                ModuleManager.getInstance().setModule(
                    interfaceName,
                    Module<IAnalytics>()
                )
            }

            IHttpInterface::class.java.simpleName -> {
                ModuleManager.getInstance().setModule(
                    interfaceName,
                    Module<IHttpInterface>()
                )
            }
        }

    }
}