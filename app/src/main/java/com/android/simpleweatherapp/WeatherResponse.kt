package com.android.simpleweatherapp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class WeatherResponse {

    @SerializedName("weather")
    @Expose
    var weather: List<Weather>? = null
    @SerializedName("base")
    @Expose
    var base: String? = null
    @SerializedName("main")
    @Expose
    var main: Main? = null
    @SerializedName("visibility")
    @Expose
    var visibility: Int? = null
    @SerializedName("wind")
    @Expose
    var id: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("cod")
    @Expose
    var cod: Int? = null

}