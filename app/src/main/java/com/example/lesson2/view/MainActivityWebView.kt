package com.example.lesson2.view

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.annotation.RequiresApi
import com.example.lesson2.databinding.ActivityMainWebviewBinding
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

class MainActivityWebView : AppCompatActivity() {

    lateinit var binding: ActivityMainWebviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainWebviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.okAppCompatButton.setOnClickListener {
            showUrl(binding.editText.text.toString())
        }
    }

    //00:51:00 Lesson5
    @SuppressLint("NewApi")
    fun showUrl(urlString: String) { //полученную строку urlString нужно спарсить в URI (URL - это подраздел URI). URI это не только про интернет. URI может быть например адрес к.-либо файла в фаиловой системе. URL более абстрактное понятие, некий адрес чего-либо в сети.
        val url = URL(urlString) //Передаем urlString в URL (import: URL.java.net)
        /*2й ВАРИАНТ указания на главный поток:
        val handler = Handler() //Получаем указатель на текущий поток (Looper не указывается явно)
        val handler = Handler(Looper.myLooper()!!) //Получаем указатель на текущий поток*/
        // (!!!) Работа с сетью в главном потоке запрещена, иначе приложение начнет фризить (лагать).
        Thread {
            val urlConnection =
                url.openConnection() as HttpsURLConnection//создаем connection на основании URI
            urlConnection.requestMethod = "GET"
            urlConnection.readTimeout =
                10000 //Ждем ответа 10 сек., если ответа нет, то больше ждать не имеет смысла
            val reader =
                BufferedReader(InputStreamReader(urlConnection.inputStream)) //reader для считывания ответа с коннекта (считывается по строкам, поэтому выбираем BufferedReader, в котором создаем входящий поток InputStreamReader() на основа нашего коннекта urlConnection), из нашего urlConnection получаем inputStream, который передается в наш BufferedReader через InputStreamReader.
            //Теперь все строки, которые приходят в наш буфер, нам необходимо объединить в одну
            val result = getLines(reader)
            //Осталось вызвать его во web view
            // 1й ВАРИАНТ
            runOnUiThread {
                //binding.webView.loadData(result, "text/html; charset=utf-8","utf-8")//Если не будет работать этот вариант(это м.б. из-за проблем с движком), то используем следующий:
                binding.webView.loadDataWithBaseURL(null, result, "text/html; charset=utf-8","utf-8", null)
            } //получаем html код. My type - "text/html; charset=utf-8" - тип контента, который мы получили
            /* 2й ВАРИАНТ
            handler.post {binding.webView.loadData(result, "text/html; charset=utf-8","utf-8")} //получаем html код. My type - "text/html; charset=utf-8" - тип контента, который мы получили*/
            /* 3й ВАРИАНТ
            val handler = Handler(Looper.getMainLooper()) //Говорим луперу: получи главный поток (getMainLooper())
            handler.post {binding.webView.loadData(result, "text/html; charset=utf-8","utf-8")} //получаем html код. My type - "text/html; charset=utf-8" - тип контента, который мы получили*/
            //Отключаем коннектор
            urlConnection.disconnect()
        }.start()
    }

    //1. Добавляем доступ в интернет в Манифест <uses-permission android:name="android.permission.INTERNET"/>
    //2. Создаем отдельный поток для работы с сетью
    //3. Т.к. с view нельзя работать не в главном потоке, нужно включить перенести ее в главный поток


    //Метод склеивания строк
    @RequiresApi(Build.VERSION_CODES.N)
    private fun getLines(reader: BufferedReader): String {
        return reader.lines().collect(Collectors.joining("\n"))
    }
}

//01:30:00


//01:23:00 Lesson5
//Кликаем по кнопке на сайте - это равно переходу по ссылке, которая находится в коде страницы. Т.е. открываем код web страницы, находим ссылку для перехода на адрес страницы, которая должна открываться при нажатии на эту кнопку и используем эту ссылку для работы в приложении.

//00:51:00 Lesson5

//Начинаем использовать класс https URL connection, который позволяет организовать работу
//по всей отправке-получению.
//Задача - получить объект класса https URL connection, далее получить connection и с ним работать:
//- открывать буфер на чтение;
//- обрабатывать этот буфер;
//- выводить этот буфер через движок.


//override fun onCreate(savedInstanceState: Bundle?) {
//    super.onCreate(savedInstanceState)
//    setContentView(R.layout.activity_main)

/*if (savedInstanceState == null)
    supportFragmentManager.beginTransaction()
        .replace(R.id.fragment_container, MainFragment.newInstance()).commit()*/

//Lesson3.kt
//        val lesson = Lesson3()
//        lesson.mainSecondPart()
//        lesson.mainSecondPart2(this)
//Lesson3.kt

//        main1()
//        LambdaJava.main()
//}