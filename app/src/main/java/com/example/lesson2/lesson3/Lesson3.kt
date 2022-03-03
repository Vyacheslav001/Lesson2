package com.example.lesson2.lesson3

import android.app.Application
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class Lesson3 {
    //~41:00
    val bar = AppCompatActivity().getSupportActionBar() //@Nullable тип
    val menu = AppCompatActivity().menuInflater //@NonNull тип
    val appNotNullable: Application =
        AppCompatActivity().application //Третий тип без аннотации (с восклицательным знаком) самостоятельно решаем его тип добавлением или не добавлением знака вопроса
    val appNullable: Application? =
        AppCompatActivity().application //Третий тип без аннотации (с восклицательным знаком) самостоятельно решаем его тип добавлением или не добавлением знака вопроса

    fun mainFirstPart() {
        var notNullable: String = ""
        var nullable: String? = ""
        nullable = null
//        val testObj: Test? = Test()

        //
//        notNullable = testObj?.stringTest ?: ""
//        //та же самая запись развернуто
//        notNullable = if (testObj?.stringTest != null) {
//            testObj.stringTest
//            // (!!!) return нельзя писать, так как он "пробивает" блок if и пытается вернуть значение на верхний уровень (в данном случае в саму функцию)
//            //return testObj?.stringTest
//            //if всегда возвращает последнюю строку блока
//        } else {
//            ""
//        }
        //

//        //ВЫСТРЕЛ СЕБЕ В НОГУ! nullpointerexception
//        notNullable = testObj!!.stringTest


//        var testObj: Test? = Test()
//        testObj = null
    }

    //1:00:00 Массивы и коллекции
    fun mainSecondPart() {
        //val phrase: Array<String> = arrayOf("first", "second")
        //Можно записать сокращенно, компилятор сам поймет тип
        val phrase = arrayOf("first", "second")
        val word = phrase[1]
        phrase[1] = "secondNew"
        phrase[2] = "third"
        phrase.size

        class Person(val name: String, var age: Int)

        val people: List<Person> = listOf(Person("Максим", 25), Person("Оля", 20))
        people[0].age = 26
        val ant = mutableListOf(Person("Максим", 25), "")
        (ant[0] as Person).age = 26
        //(ant[0] as? Person).age = 26 //Если Person nullable, то as со знаком ?
        ant[1] = "26"

        val peopleHack: MutableList<Person> =
            people.toMutableList() //toMutableList() это extension функция (функция расширения)
        peopleHack.add(Person("Новый", 0))

//1:30:00
//Extension функции
        var myInt: Int = 2
        Log.d("myLogs", "${myInt.mySquare()} $myInt") //1:37:00 простое форматирование строк
    }

    fun Int.mySquare(): Int {
        return this * this
    }
}
//

//class Test {
//    //43:25
//    // Safe call - "?."
//    val stringTest: String = "test"
//
//}

//43:25

