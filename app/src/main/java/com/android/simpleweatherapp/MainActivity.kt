package com.android.simpleweatherapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val api = WeatherApi.create
        api.retrieveWeather("ad596f7f0873725a2ccf6195a72d3b37", "London")
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe()
    }
}
