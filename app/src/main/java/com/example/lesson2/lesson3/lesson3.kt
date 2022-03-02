package com.example.lesson2.lesson3

import android.app.Application
import androidx.appcompat.app.AppCompatActivity

class lesson3 {

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
        val testObj: Test? = Test()

        //
        notNullable = testObj?.stringTest ?: ""
        //та же самая запись развернуто
        notNullable = if (testObj?.stringTest != null) {
            testObj.stringTest
            // (!!!) return нельзя писать, так как он "пробивает" блок if и пытается вернуть значение на верхний уровень (в данном случае в саму функцию)
            //return testObj?.stringTest
            //if всегда возвращает последнюю строку блока
        } else {
            ""
        }
        //

        //ВЫСТРЕЛ СЕБЕ В НОГУ! nullpointerexception
        notNullable = testObj!!.stringTest


//        var testObj: Test? = Test()
        testObj = null
    }

    //1:00:00 Массивы и коллекции
    fun mainSecondPart(){

    }
}

class Test {
    //43:25
    // Safe call - "?."
    val stringTest: String = "test"

}

//43:25