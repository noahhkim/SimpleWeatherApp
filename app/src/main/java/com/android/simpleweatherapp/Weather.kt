package com.android.simpleweatherapp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Weather {
    @SerializedName("main")
    @Expose
    var main: Main? = null
}
