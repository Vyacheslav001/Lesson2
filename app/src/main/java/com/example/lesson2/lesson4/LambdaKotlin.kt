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


        printMy(valAnonymous)
        printMy(valLambda)

        //1:15:00 Как передаются параметры в лямбду
        val l3 = { int1: Int, int2: Int ->
            Log.d("MyLogs", "1")
            Log.d("MyLogs", "2")
            Log.d("MyLogs", "3")
            Unit
        }
        l3(1, 3)
        //Пока в l3 сидит лямбда, но когда мы ее вызовем, то тогда значение будет Unit:
        val l4 = l3(2, 4)

        //Если входных аргументов нет, то и стрелочки не будет
        val l3_1 = {
            Log.d("MyLogs", "1")
            Log.d("MyLogs", "2")
            Log.d("MyLogs", "3")
            Unit
        }
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

        //1:33:00
        //Функции высшего порядка - это функции, которые могут принимать на вход и возвращить другие функции, к ним относятся лямбды, extension-функции и делегаты

        val people: List<Person> = listOf(Person("name1", 10), Person("name2", 20))
        //Этапы упрощения кода
//        people.forEach({ person: Person -> print(person) })
//        people.forEach({ person: Person -> Log.d("MyLogs", "${person.name} ${person.age}") })
//        people.forEach(){ person: Person -> Log.d("MyLogs", "${person.name}") }
//        people.forEach { person: Person -> Log.d("MyLogs", "${person.name}") }
//        people.forEach { person -> Log.d("MyLogs", "${person.name}") }
//        people.forEach { Log.d("MyLogs", "${it.name}") }
        people.forEach { it.print() }
        people.forEach { print2(it) }

        //1:50:00
        val testObj = Test()
        testObj.test() //Сработает внутренняя функция, если она не private
        test(testObj) //Сработает внешняя функция "outer fun"
    }

    data class Person(var name: String, val age: Int)
    fun print2(person:Person){
        Log.d("MyLogsLambs2", "${person.name} ${person.age}")
    }
    //Тоже самое, но в виде extension-функции. (!!!) Есть опасность, функции расширения могут переопределять внутренние функции.
    fun Person.print(){
        Log.d("MyLogsLambs", "${this.name} ${this.age}")
    }

    //1:50:00 Проверка, какая функция сработает, внутренняя или extension (Checking, which function works, inner or extension)
    // (!) Сработала внутренняя функция. Функция расширения не задействована, что может повлечь проблемы в работе проекта, если за этим не уследить
    class Test{
        private fun test(){
            Log.d("MyLogs", "inner fun")
        }
    }
    //Extension-функция

    private fun test(test: Test){
        Log.d("MyLogs", "outer fun")
    }
}

fun LambdaKotlin.Test.test(){
    Log.d("MyLogs", "extension fun")
}

fun LambdaKotlin.Person.print3(): String{
    Log.d("MyLogsLambs2", "${this.name} ${this.age}")
    return ""
}

//1:57:50