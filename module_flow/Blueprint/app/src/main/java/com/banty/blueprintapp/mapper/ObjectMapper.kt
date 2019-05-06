package com.banty.blueprintapp.mapper

import android.util.Log
import com.banty.core.signal.Signal
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader


/**
 * Created by Banty on 2019-05-06.
 */
class ObjectMapper(private val inputStream: InputStream) {

    /**
     * Contains the mapping of Signals and corresponding fragments.
     * */
    val signalFragmentMap = HashMap<String, String>()

    companion object {
        private var INSTANCE: ObjectMapper? = null

        fun getObjectMapper(inputStream: InputStream): ObjectMapper {
            if (INSTANCE == null) {
                synchronized(ObjectMapper::class) {
                    INSTANCE = ObjectMapper(inputStream)
                }
            }
            return INSTANCE!!
        }
    }

    fun prepareMap() : ObjectMapper{
        val reader = BufferedReader(InputStreamReader(inputStream))
        var line = reader.readLine()
        while (line != null) {
            val signalClassNameSplit = line.split("@")
            signalFragmentMap[signalClassNameSplit[0]] = signalClassNameSplit[1]
            line = reader.readLine()
        }
        return this
    }

    fun getClassName(signal: Signal): Class<*> {
        val className = signalFragmentMap[signal.toString()]
        try {
            if (className != null)
                return Class.forName(className)
        } catch (ex: ClassNotFoundException) {
            Log.e("Viu", "Class not found", ex)
        } catch (ex: Exception) {
            Log.e("Viu", "Exception while getting class : ", ex)
        }
        throw IllegalStateException("Signal - Class mapping is not found. Check the class name in the navigator.txt file")
    }
}