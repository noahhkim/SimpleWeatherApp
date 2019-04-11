package com.android.simpleweatherapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val apiKey = "ad596f7f0873725a2ccf6195a72d3b37"
    val imperial = "imperial"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initSearchInput()

    }

    private fun getWeatherData(userInput: String): Disposable {
        return WeatherApi.create.retrieveWeather(apiKey,
                userInput,
                imperial)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    updateViews(response)
                },
                        { error ->
                            Log.e("Error: ", error.localizedMessage)
                        })
    }

    private fun updateViews(weatherResponse: WeatherResponse) {
        locationText.text = weatherResponse.name
        val temp = weatherResponse.main.temp
        degreeText.text = temp.toString().plus("\u00B0").plus(" F")
        weatherResponse.weather.map { weatherText.text = it.main }

        val color = when (temp.toInt()) {
            in 20..45 -> R.color.cold
            in 46..65 -> R.color.cool
            in 66..85 -> R.color.warm
            in 86..200 -> R.color.hot
            else -> R.color.freezing
        }
        search_background.setBackgroundColor(ContextCompat.getColor(this, color))
    }


    private fun initSearchInput(){
        editText.setOnEditorActionListener { v, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    getWeatherData(v.text.toString())
                    true
                }
                else -> false
            }
        }
    }
}
