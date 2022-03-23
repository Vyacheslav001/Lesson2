package com.example.lesson2.view.details

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.lesson2.databinding.FragmentDetailsBinding
import com.example.lesson2.domain.Weather
import com.example.lesson2.repository.WeatherDTO
import com.example.lesson2.repository.WeatherLoader
import com.example.lesson2.repository.WeatherLoaderListener

class DetailsFragment : Fragment(), WeatherLoaderListener {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    //Можно создать listener отдельно, а можно сам фрагмент сделать листенером
//    val listener = object : WeatherLoaderListener{
//        override fun onLoaded(weatherDTO: WeatherDTO) {
//            TODO("Not yet implemented")
//        }
//        override fun onFailed(throwable: Throwable) {
//            TODO("Not yet implemented")
//        }
//    }

    companion object {
        /*fun newInstance(): Fragment {
            return MainFragment()
        }
        или коротко: */
        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }

        const val BUNDLE_WEATHER_KEY = "key"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return inflater.inflate(R.layout.fragment_main, container, false)
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    val localWeather: Weather by lazy {
        arguments?.getParcelable(BUNDLE_WEATHER_KEY) ?: Weather()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        WeatherLoader(this, localWeather.city.lat, localWeather.city.lon).loadWeather()
    }

    private fun showWeather(weatherDTO: WeatherDTO) {
        with(binding) {
//            with(weatherDTO) {
                cityName.text = localWeather.city.name
                cityCoordinates.text = ("lat ${localWeather.city.lat}\nlon ${localWeather.city.lon}")
                temperatureValue.text = weatherDTO.fact.temp.toString()
                //temperatureValue.text = "${weatherDTO.factDTO.temp}"//можно записать и так
                feelsLikeValue.text = weatherDTO.fact.feels_like.toString()
                weatherCondition.text = weatherDTO.fact.condition
//            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onLoaded(weatherDTO: WeatherDTO) {
        showWeather(weatherDTO)
    }

    override fun onFailed(throwable: Throwable) {
        //ДЗ вывести к примеру Snackbar
    }
}

//2:53:00