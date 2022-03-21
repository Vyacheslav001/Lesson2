package com.example.lesson2.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lesson2.R
import com.example.lesson2.databinding.ActivityMainBinding
import com.example.lesson2.view.main.MainFragment


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, MainFragment.newInstance()).commit()
    }
}

//01:56:20 Lesson5
