package com.example.lesson2.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lesson2.R
import com.example.lesson2.lesson3.Lesson3

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, MainFragment.newInstance()).commit()

        //Lesson3.kt
//        val lesson = Lesson3()
//        lesson.mainSecondPart()
//        lesson.mainSecondPart2(this)
        //Lesson3.kt
    }
}

//1:51:52