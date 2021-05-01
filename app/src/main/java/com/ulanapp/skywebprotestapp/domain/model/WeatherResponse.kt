package com.ulanapp.skywebprotestapp.domain.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("coord") var coord: Coord? = null,
    @SerializedName("weather") var weather: List<Weather>? = null,
    @SerializedName("base") var base: String? = null,
    @SerializedName("main") var main: Main? = null,
    @SerializedName("visibility") var visibility: Int? = null,
    @SerializedName("wind") var wind: Wind? = null,
    @SerializedName("clouds") var clouds: Clouds? = null,
    @SerializedName("dt") var dt: Int? = null,
    @SerializedName("sys") var sys: Sys? = null,
    @SerializedName("timezone") var timezone: Int? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("cod") var cod: Int? = null
) {

    data class Main(
        @SerializedName("temp") var temp: Float? = null,
        @SerializedName("feels_like") var feelsLike: Float? = null,
        @SerializedName("temp_min") var tempMin: Float? = null,
        @SerializedName("temp_max") var tempMax: Float? = null,
        @SerializedName("pressure") var pressure: Int? = null,
        @SerializedName("humidity") var humidity: Int? = null
    )

    data class Sys(
        @SerializedName("type") var type: Int? = null,
        @SerializedName("id") var id: Int? = null,
        @SerializedName("country") var country: String? = null,
        @SerializedName("sunrise") var sunrise: Int? = null,
        @SerializedName("sunset") var sunset: Int? = null
    )

    data class Weather(
        @SerializedName("id") var id: Int? = null,
        @SerializedName("main") var main: String? = null,
        @SerializedName("description") var description: String? = null,
        @SerializedName("icon") var icon: String? = null
    )

    data class Wind(
        @SerializedName("speed") var speed: Int? = null,
        @SerializedName("deg") var deg: Int? = null
    )

    data class Clouds(
        @SerializedName("all") var all: Int? = null
    )

    data class Coord(
        @SerializedName("lon") var lon: Float? = null,
        @SerializedName("lat") var lat: Float? = null,
    )
}