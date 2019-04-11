package com.android.simpleweatherapp

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("/data/2.5/weather")
    fun retrieveWeather(@Query("appid") apiKey: String, @Query("q") city: String):
            Observable<WeatherResponse>

    companion object Factory {
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val create = retrofit.create(WeatherApi::class.java)

    }

}