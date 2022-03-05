package com.example.lesson2.lesson3

import android.app.Application
import android.content.Context
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import java.security.AccessControlContext

class Lesson3 {
    //~41:00
//    val bar = AppCompatActivity().getSupportActionBar() //@Nullable тип
//    val menu = AppCompatActivity().menuInflater //@NonNull тип
//    val appNotNullable: Application =
//        AppCompatActivity().application //Третий тип без аннотации (с восклицательным знаком) самостоятельно решаем его тип добавлением или не добавлением знака вопроса
//    val appNullable: Application? =
//        AppCompatActivity().application //Третий тип без аннотации (с восклицательным знаком) самостоятельно решаем его тип добавлением или не добавлением знака вопроса

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
//        val phrase = arrayOf("first", "second")
//        val word = phrase[1]
//        phrase[1] = "secondNew"
//        phrase[2] = "third"
//        phrase.size
//
//        class Person(val name: String, var age: Int)
//
//        val people: List<Person> = listOf(Person("Максим", 25), Person("Оля", 20))
//        people[0].age = 26
//        val ant = mutableListOf(Person("Максим", 25), "")
//        (ant[0] as Person).age = 26
//        //(ant[0] as? Person).age = 26 //Если Person nullable, то as со знаком ?
//        ant[1] = "26"
//
//        val peopleHack: MutableList<Person> =
//            people.toMutableList() //toMutableList() это extension функция (функция расширения)
//        peopleHack.add(Person("Новый", 0))

//1:30:00
//Extension функции
        val myInt: Int = 2
        Log.d("myExtensionLogs", "${myInt.mySquare()} $myInt") //1:37:00 простое форматирование строк
    }

    fun Int.mySquare(): Int {
        return this * this
    }
//

    //1:49:00
    //Дженерики
    fun mainSecondPart2(context: Context) {
        data class Person(val name: String, var age: Int)

        val people: List<Person> = listOf(Person("Максим", 25), Person("Оля", 20))

        write(1)
        write(1.0)
        write("")
        write(1f)
        write(people[0])

        //Это соответствует следующим записям

        writeInt(1)
        writeDouble(1.0)
        writeString("")
        writeFloat(1f)
//        writePerson(people[0]) //почему-то не работает

        val btn = Button(context)
        val layout = LinearLayout(context)
//        val view1 = Generic2(btn) //btn не принимает, т.к. мы ограничили дженерик <T: ViewGroup>
        val view1 = Generic2(layout)
    }

    class Person(val name: String, var age: Int)

    private fun writeInt(input: Int) = Log.d("mylogs", input.toString())
    private fun writeDouble(input: Double) = Log.d("mylogs", input.toString())
    private fun writeString(input: String) = Log.d("mylogs", input.toString())
    private fun writeFloat(input: Float) = Log.d("mylogs", input.toString())
    private fun writePerson(input: Person) = Log.d("mylogs", input.toString())

    fun <T> write(input: T) = Log.d("mylogs", input.toString())
    fun <T, G, J> writeTriple(input1: T, input2: G, input3: J) = Log.d("mylogs", input1.toString())


    //Передача дженериков в классы
    class Generic<Type1, Type2>(val field1: Type1, val field2: Type2)
    class Generic2<T : ViewGroup>(val field1: T)
//2:10:00 Дженерики конец
}

//class Test {
//    //43:25
//    // Safe call - "?."
//    val stringTest: String = "test"
//
//}

//1:43:15
interface Test2 {
    val string: String // (??) В Java переменную нужно сразу инициализировать, в Котлине нет, но можно через геттер
        get() = ""

    fun withImpl(): String {
        return ""
    }

    fun withoutImpl(): String
}

//1:48:45
//Unit это тоже, что и void в Java

//2:06:00

