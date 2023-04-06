package com.mrgelatine.citytimezones

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*


class CountryViewModel: ViewModel() {
    val apiKey:String = "Y04YOJWHYZT2"
    val adapter:Adapter = Adapter()
    var data:MutableLiveData<MutableList<CityRow>> = MutableLiveData<MutableList<CityRow>>().apply {
        var dataP = this
        val retrofit = Retrofit.Builder().baseUrl("http://api.timezonedb.com/v2.1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val jsonPlaceHolderApi = retrofit.create(
            JsonPlaceHolderApi::class.java
        )

        val zonesCall: Call<TimeZones?>? = jsonPlaceHolderApi.getCities(apiKey,"json")

        if (zonesCall != null) {
            zonesCall.enqueue(object : Callback<TimeZones?> {
                override fun onResponse(call: Call<TimeZones?>, response: Response<TimeZones?>) {
                    if (!response.isSuccessful()) {
                        return
                    }
                    val zones= ((response.body() as TimeZones).zones as MutableList<Zone>?)!!
                    val currentGMT = Calendar.getInstance()
                    currentGMT.add(Calendar.MILLISECOND, -currentGMT.timeZone.getOffset(currentGMT.timeInMillis))
                    val temp:MutableList<CityRow> = mutableListOf()
                    for(zone in zones){
                        temp.add(CityRow(zone.zoneName.split("/")[1].replace('_',' '),zone.gmtOffset,
                            currentGMT.clone() as Calendar
                        ))
                    }
                    dataP.value = temp
                }

                override fun onFailure(call: Call<TimeZones?>?, t: Throwable) {
                }
            })
        }
    }

}