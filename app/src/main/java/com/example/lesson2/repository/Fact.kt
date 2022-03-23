package com.example.lesson2.repository

/*
Copyright (c) 2022 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */

/*
data class Fact(

    @SerializedName("obs_time") val obs_time: Int,
    @SerializedName("uptime") val uptime: Int,
    @SerializedName("temp") val temp: Int,
    @SerializedName("feels_like") val feels_like: Int,
    @SerializedName("icon") val icon: String,
    @SerializedName("condition") val condition: String,
    @SerializedName("cloudness") val cloudness: Int,
    @SerializedName("prec_type") val prec_type: Int,
    @SerializedName("prec_prob") val prec_prob: Int,
    @SerializedName("prec_strength") val prec_strength: Int,
    @SerializedName("is_thunder") val is_thunder: Boolean,
    @SerializedName("wind_speed") val wind_speed: Int,
    @SerializedName("wind_dir") val wind_dir: String,
    @SerializedName("pressure_mm") val pressure_mm: Int,
    @SerializedName("pressure_pa") val pressure_pa: Int,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("daytime") val daytime: String,
    @SerializedName("polar") val polar: Boolean,
    @SerializedName("season") val season: String,
    @SerializedName("source") val source: String,
    @SerializedName("accum_prec") val accum_prec: Accum_prec,
    @SerializedName("soil_moisture") val soil_moisture: Double,
    @SerializedName("soil_temp") val soil_temp: Int,
    @SerializedName("uv_index") val uv_index: Int,
    @SerializedName("wind_gust") val wind_gust: Double
)

// To parse the JSON, install Klaxon and do:
//
//   val welcome1 = Welcome1.fromJson(jsonString)

package codebeautify

import com.beust.klaxon.*

private val klaxon = Klaxon()

data class Welcome1 (
    val now: Long,

    @Json(name = "now_dt")
    val nowDt: String,

    val info: Info,
    val fact: Fact,
    val forecast: Forecast
) {
    public fun toJson() = klaxon.toJsonString(this)

    companion object {
        public fun fromJson(json: String) = klaxon.parse<Welcome1>(json)
    }
}

data class Fact (
    @Json(name = "obs_time")
    val obsTime: Long,

    val temp: Long,

    @Json(name = "feels_like")
    val feelsLike: Long,

    val icon: String,
    val condition: String,

    @Json(name = "wind_speed")
    val windSpeed: Double,

    @Json(name = "wind_dir")
    val windDir: String,

    @Json(name = "pressure_mm")
    val pressureMm: Long,

    @Json(name = "pressure_pa")
    val pressurePa: Long,

    val humidity: Long,
    val daytime: String,
    val polar: Boolean,
    val season: String,

    @Json(name = "wind_gust")
    val windGust: Double
)

data class Forecast (
    val date: String,

    @Json(name = "date_ts")
    val dateTs: Long,

    val week: Long,
    val sunrise: String,
    val sunset: String,

    @Json(name = "moon_code")
    val moonCode: Long,

    @Json(name = "moon_text")
    val moonText: String,

    val parts: List<Part>
)

data class Part (
    @Json(name = "part_name")
    val partName: String,

    @Json(name = "temp_min")
    val tempMin: Long,

    @Json(name = "temp_avg")
    val tempAvg: Long,

    @Json(name = "temp_max")
    val tempMax: Long,

    @Json(name = "wind_speed")
    val windSpeed: Double,

    @Json(name = "wind_gust")
    val windGust: Double,

    @Json(name = "wind_dir")
    val windDir: String,

    @Json(name = "pressure_mm")
    val pressureMm: Long,

    @Json(name = "pressure_pa")
    val pressurePa: Long,

    val humidity: Long,

    @Json(name = "prec_mm")
    val precMm: Long,

    @Json(name = "prec_prob")
    val precProb: Long,

    @Json(name = "prec_period")
    val precPeriod: Long,

    val icon: String,
    val condition: String,

    @Json(name = "feels_like")
    val feelsLike: Long,

    val daytime: String,
    val polar: Boolean
)

data class Info (
    val url: String,
    val lat: Double,
    val lon: Double
)
*///