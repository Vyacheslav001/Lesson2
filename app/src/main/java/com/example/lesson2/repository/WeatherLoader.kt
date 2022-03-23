package com.example.lesson2.repository

import android.os.Build
import android.os.Handler
import android.os.Looper
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

//Это класс-оболочка вокруг одной единственной функции - загрузка погоды

class WeatherLoader(val listener: WeatherLoaderListener,val lat: Double, val lon: Double) {
    fun loadWeather() {
        val url = URL("https://api.weather.yandex.ru/v2/informers?lat=${lat}&lon=${lon}")
        Thread {
            val urlConnection = url.openConnection() as HttpsURLConnection
            urlConnection.requestMethod = "GET"
            //Нужно передать ключ:
            urlConnection.addRequestProperty("X-Yandex-API-Key", "01050e1b-58f7-43ea-8aca-c4c58af60360")
            urlConnection.readTimeout = 10000
            val reader = BufferedReader(InputStreamReader(urlConnection.inputStream))
            val weatherDTO = Gson().fromJson(reader, WeatherDTO::class.java) //Gson получает входные данные и их парсит в WeatherDTO
            val handler = Handler(Looper.getMainLooper()) //В классе нет контекста, поэтому мы не можем использовать вызов основного потока runOnUiThread, поэтому нужно использовать Handler, (2:36:25Less5) -> который получил ссылку на getMainLooper (т.е. на Looper главного потока)
            handler.post { listener.onLoaded(weatherDTO) }
            urlConnection.disconnect()
        }.start()
    }
}