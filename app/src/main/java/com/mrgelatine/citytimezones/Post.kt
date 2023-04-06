package com.mrgelatine.citytimezones

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface JsonPlaceHolderApi {
    @GET("list-time-zone")
    fun getCities(
        @Query("key") uid: String,
        @Query("format") format: String,
    ): Call<TimeZones?>?

}

class TimeZones{
    @SerializedName("zones")
    val zones: List<Zone?>? = null
}
class Zone{
    @SerializedName("zoneName")
    val zoneName:String = ""
    @SerializedName("gmtOffset")
    val gmtOffset:Int = -1
}
