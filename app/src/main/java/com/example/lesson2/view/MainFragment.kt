package com.example.lesson2.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.lesson2.R
import com.example.lesson2.databinding.FragmentMainBinding
import com.example.lesson2.domain.Weather
import com.example.lesson2.viewmodel.AppState
import com.example.lesson2.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    /*
       Полная запись
    private val binding: FragmentMainBinding
       Геттеры и сеттеры ставятся сразу после объявления свойства
    get() {
        return _binding!!
    }
       для val свойств сеттеры недопустимы!
    set(value) {
        field = value //field - обязательное ключевое слово для сеттера
    }
    */

    private lateinit var viewModel: MainViewModel

    companion object {
        /*fun newInstance(): Fragment {
            return MainFragment()
        }
        или коротко: */
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return inflater.inflate(R.layout.fragment_main, container, false)
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getLiveData().observe(/*2:12:00 Lesson2*/viewLifecycleOwner,
            Observer<AppState> { appState: AppState ->
//            Toast.makeText(context/*this@MainFragment.context*/, "It's work", Toast.LENGTH_LONG).show()
                renderData(appState)
            }) //viewLifecycleOwner следит за жизненным циклом фрагмента или активности и если фрагмента уже нет, то предупреждает об этом, чтобы
        viewModel.getDataFromRemoteSource()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                val throwable = appState.error
                Snackbar.make(binding.mainView, "ERROR $throwable NUMBER: ${viewModel.r}", Snackbar.LENGTH_LONG).show()
            }
            AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }
            is AppState.Success -> {
                binding.loadingLayout.visibility = View.GONE
                val weather = appState.weatherData
                setData(weather)
                Snackbar.make(binding.mainView, "Success NUMBER: ${viewModel.r}", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun setData(weather: Weather) {
        binding.cityName.text = weather.city.name
        binding.cityCoordinates.text = ("lat ${weather.city.lat}\nlon ${weather.city.lon}")
        binding.temperatureValue.text = weather.temperature.toString()
        binding.feelsLikeValue.text = "${weather.feelsLike}" //а можно записать так
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

//2:53:00