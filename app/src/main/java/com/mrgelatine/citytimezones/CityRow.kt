package com.mrgelatine.citytimezones

import java.text.SimpleDateFormat
import java.util.*


data class CityRow(var cityName:String, var gmtOffset:Int, var currentGMT:Calendar) {
    var currentTime:String
    init{
        var df= SimpleDateFormat("HH:mm")
        currentGMT.add(Calendar.SECOND, gmtOffset)
        currentTime = df.format(currentGMT.time)
    }
    fun refreshTime(newGMT:Calendar){
        var df= SimpleDateFormat("HH:mm")
        newGMT.add(Calendar.SECOND, gmtOffset)
        currentTime = df.format(newGMT.time)
    }
}