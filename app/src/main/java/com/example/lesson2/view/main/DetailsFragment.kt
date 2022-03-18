package com.example.lesson2.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.lesson2.databinding.FragmentDetailsBinding
import com.example.lesson2.databinding.FragmentMainBinding
import com.example.lesson2.domain.Weather
import com.example.lesson2.viewmodel.AppState
import com.example.lesson2.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val weather = it?.getParcelable<Weather>(BUNDLE_WEATHER_KEY) ?: Weather()
            setData(weather)
        }
    }

    private fun setData(weather: Weather) {
        with(binding) {
            with(weather){
                cityName.text = city.name
                cityCoordinates.text = ("lat ${city.lat}\nlon ${city.lon}")
                temperatureValue.text = temperature.toString()
                feelsLikeValue.text = "$feelsLike" //а можно записать так }
            }

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

//2:53:00