package com.example.lesson2.lesson4

import android.util.Log

//00:35:00

class LambdaKotlin {
    fun main() {
        /*val l1 = { Log.d("MyLogs", "run1")
        "run2"}
        val l2 = run { Log.d("MyLogs", "run3") //ответ лямбды это всегда последняя строка, т.е. в данном случае это run4 и она записывается в l2 после выполнения основного блока со словом run
            "run4"}
        //вместо run можно записать круглые скобки в конце блока, это одно и тоже
        val l3 = { Log.d("MyLogs", "run5")
            "run6"}()
        //return в лябмбдах не используется, либо надо ставить определитель hack@ как в этом примере
        val l4 = hack@ { Log.d("MyLogs", "run7")
            return@hack "run8"}

        val field1 = "str1"
        run {
            val field1 = "str2"
            val field2 = "str3"
        }

        Log.d("MyLogs", field1)
        Log.d("MyLogs", l1())
        Log.d("MyLogs", l2)*/


//        printMy(valAnonymous)
//        printMy(valLambda)
    }

    //53:00 Функции расширения (Extension-функции) продолжение из Lesson3

    //Функция анонимного типа (не умеет работать с return)
    val valAnonymous = fun(int1: Int, int2: Int): String {
        Log.d("MyLogs", "Зашли в valAnonymous")
        return "valAnonymous"
    }

    //Лямбде нельзя задать строгий возвращаемый тип и return в лябде пробивает строку
    val valLambda = hack@{ int1: Int, int2: Int ->
        Log.d("MyLogs", "Зашли в valLambda")
        return@hack "valLambda"
    }

    fun printMy(fun1: (int1: Int, int2: Int) -> String) {
        Log.d("MyLogs", "Зашли в printMy")
        Log.d("MyLogs", fun1(1, 2))
    }
}