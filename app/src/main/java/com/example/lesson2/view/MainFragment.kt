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
import com.example.lesson2.viewmodel.MainViewModel

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
        viewModel.getLiveData().observe(/*2:12:00 Lesson2*/viewLifecycleOwner, Observer<Any>{
            Toast.makeText(context/*this@MainFragment.context*/, "It's work", Toast.LENGTH_LONG).show()
        }) //viewLifecycleOwner следит за жизненным циклом фрагмента или активности и если фрагмента уже нет, то предупреждает об этом, чтобы
        viewModel.getDataFromRemoteSource()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}