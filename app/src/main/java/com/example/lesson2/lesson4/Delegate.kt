package com.example.lesson2.lesson4

import android.util.Log

//2:54:40 Lesson4
//Блок by lazy вызывается только один раз - в момент инициализации переменной
    val word by lazy { //Ключевое слово by lazy служит для отложенной инициализации через механизм делегатов. Делегат lazy принимает лямбда-выражение с кодом, который вы бы хотели выполнить для инициализации свойства. Иногда требуется объявить переменную, но отложить инициализацию. Разница с lateinit в том, что lateinit нужно будет в любом случае в дальнейшем инициализировать, а lazy не требует в дальнейшем инициализации.
        Log.d("MyLogsDelegate", "word by lazy")
        "word1"
    }

    fun main1() {
        val baseImpl = BaseImpl()
        val delegate = Delegate(baseImpl, baseImpl)
//    Log.d("MyLogsDelegate", delegate.nameBase1)
//    Log.d("MyLogsDelegate", delegate.nameBase2)
        delegate.funBase1()
        delegate.funBase2()
        Log.d("MyLogsDelegate", word)
        Log.d("MyLogsDelegate", word)
        Log.d("MyLogsDelegate", word)
        Log.d("MyLogsDelegate", word)
        Log.d("MyLogsDelegate", word)
    }

class Delegate(base1: Base1, base2: Base2) : Base1 by base1, Base2 by base2

class BaseImpl : Base1, Base2 {
    override fun funBase1() {
        Log.d("MyLogsDelegate", "funBase1")
    }

    override val nameBase1 = ""

    override fun funBase2() {
        Log.d("MyLogsDelegate", "funBase2")
    }

    override val nameBase2 = ""
}

interface Base1 {
    fun funBase1()
    val nameBase1: String
}

interface Base2 {
    fun funBase2()
    val nameBase2: String
}

