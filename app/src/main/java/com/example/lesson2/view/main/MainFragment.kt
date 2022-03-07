package com.example.lesson2.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.lesson2.R
import com.example.lesson2.databinding.FragmentMainBinding
import com.example.lesson2.domain.Weather
import com.example.lesson2.view.OnItemViewClickListener
import com.example.lesson2.viewmodel.AppState
import com.example.lesson2.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment(), OnItemViewClickListener {

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
    private var isDataSetRus: Boolean = true
    private var adapter = MainFragmentAdapter()
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
        binding.mainFragmentRecyclerView.adapter = adapter
        adapter.setOnItemViewClickListener(this)
        binding.mainFragmentFAB.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                isDataSetRus = !isDataSetRus
//                viewModel.getDataFromLocalSource(isDataSetRus) //можно написать так, либо как в методичке
                if (isDataSetRus) {
                    viewModel.getWeatherFromLocalSourceRus()
                    binding.mainFragmentFAB.setImageResource(R.drawable.ic_earth)
                } else {
                    viewModel.getWeatherFromLocalSourceWorld()
                    binding.mainFragmentFAB.setImageResource(R.drawable.ic_russia)
                }
            }
        })
        viewModel = ViewModelProvider(this).get(
            MainViewModel::
            class.java
        )
        viewModel.getLiveData().observe(/*2:12:00 Lesson2*/viewLifecycleOwner,
            Observer<AppState>
            { appState: AppState ->
//            Toast.makeText(context/*this@MainFragment.context*/, "It's work", Toast.LENGTH_LONG).show()
                renderData(appState)
            }) //viewLifecycleOwner следит за жизненным циклом фрагмента или активности и если фрагмента уже нет, то предупреждает об этом, чтобы
        viewModel.getWeatherFromLocalSourceRus()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> {
                binding.mainFragmentLoadingLayout.visibility = View.GONE
                val throwable = appState.error
//                Snackbar.make(
//                    binding.root,
//                    "ERROR $throwable NUMBER: ${viewModel.r}",
//                    Snackbar.LENGTH_LONG
//                ).show()
            }
            AppState.Loading -> {
                binding.mainFragmentLoadingLayout.visibility = View.VISIBLE
            }
            is AppState.Success -> {
                binding.mainFragmentLoadingLayout.visibility = View.GONE
                val weather = appState.weatherData
                adapter.setWeather(weather)
//                Snackbar.make(
//                    binding.root,
//                    "Success NUMBER: ${viewModel.r}",
//                    Snackbar.LENGTH_LONG
//                ).show()
            }
        }
    }

//    private fun setData(weather: List<Weather>) {
//        binding.cityName.text = weather.city.name
//        binding.cityCoordinates.text = ("lat ${weather.city.lat}\nlon ${weather.city.lon}")
//        binding.temperatureValue.text = weather.temperature.toString()
//        binding.feelsLikeValue.text = "${weather.feelsLike}" //а можно записать так
//    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onItemClick(weather: Weather) {
        val bundle = Bundle()
        bundle.putParcelable(DetailsFragment.BUNDLE_WEATHER_KEY, weather)
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, DetailsFragment.newInstance(bundle))
            .addToBackStack("") //без addToBackStack приложение будет закрываться при нажатии кнопки "назад"
            .commit()
    }
}

//3:34:00