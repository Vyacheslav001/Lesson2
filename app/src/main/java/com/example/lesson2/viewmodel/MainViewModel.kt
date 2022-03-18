package com.example.lesson2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lesson2.domain.Weather
import com.example.lesson2.repository.RepositoryImpl
import java.lang.IllegalStateException
import java.lang.Thread.sleep
import kotlin.random.Random

class MainViewModel(
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val repositoryImpl: RepositoryImpl = RepositoryImpl(),
) : ViewModel() {
    /*
    fun getLiveData(): LiveData<Any>{
        return liveDataToObserve
    }
    */
    val r: Int = Random.nextInt(10)
    fun getLiveData() = liveDataToObserve

    fun getWeatherFromLocalSourceWorld() = getDataFromLocalSource(false)

    fun getWeatherFromLocalSourceRus() = getDataFromLocalSource(true)

    private fun getDataFromLocalSource(isRussian: Boolean) {

        with(liveDataToObserve){
        postValue(AppState.Loading)
        Thread {
            sleep(1000)
            if (isRussian) {
                postValue(AppState.Success(repositoryImpl.getWeatherFromLocalStorageRus()))
            } else {
                postValue(AppState.Success(repositoryImpl.getWeatherFromLocalStorageWorld()))
            }
        }.start()
        }
    }
}

//ДЗ Lesson2

//val r: Int = Random.nextInt(10)

//    fun getDataFromRemoteSource() {
//        liveDataToObserve.postValue(AppState.Loading)
//        Thread {
//            sleep(2000)
//            if (r > 5) {
//                liveDataToObserve.postValue(AppState.Success(repositoryImpl.getWeatherFromLocalSource()))
//            } else {
//                liveDataToObserve.postValue(AppState.Error(IllegalStateException()))
//            }
//        }.start()
//    }