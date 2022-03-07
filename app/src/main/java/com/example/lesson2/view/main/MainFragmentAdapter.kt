package com.example.lesson2.view.main

import android.view.LayoutInflater
import android.view.OnReceiveContentListener
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson2.R
import com.example.lesson2.domain.Weather
import com.example.lesson2.view.OnItemViewClickListener

//Адаптер что-то хранит, т.к. он находится между массивом и view

class MainFragmentAdapter : RecyclerView.Adapter<MainFragmentAdapter.MainFragmentViewHolder>() {

    private var weatherData: List<Weather> = listOf()
//        set(value) { //Меняем сеттер, используем теневое поле "field", но это не желательный код
//            field = value
//            notifyDataSetChanged()
//        }
    //Сеттер для weatherData можно написать в отдельном методе
    fun setWeather(data: List<Weather>) {
        weatherData = data
        notifyDataSetChanged()
    }
    private lateinit var listener: OnItemViewClickListener

    fun setOnItemViewClickListener(onItemViewClickListener: OnItemViewClickListener){
        listener = onItemViewClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainFragmentViewHolder {
        val holder = MainFragmentViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_main_recycler_item, parent, false)
        )
        return holder
    }

    override fun onBindViewHolder(holder: MainFragmentViewHolder, position: Int) {
        holder.render(weatherData[position])
    }

    override fun getItemCount() = weatherData.size

    inner class MainFragmentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun render(weather: Weather) {
            itemView.findViewById<TextView>(R.id.mainFragmentRecyclerItemTextView).text =
                weather.city.name
            itemView.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    Toast.makeText(itemView.context, "WORK", Toast.LENGTH_SHORT).show()
                    listener.onItemClick(weather)
                }
            })
        }
    }
}