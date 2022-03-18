package com.example.lesson2.lesson4

import android.util.Log

//2:00:00
class Extension {
    fun main() {

//with
        // 2:01:00 Lesson4
        val people: List<LambdaKotlin.Person> = listOf(
            LambdaKotlin.Person("name1", 10),
            LambdaKotlin.Person("name2", 20)
        )
        people.indexOf(people[0])
        people.indexOf(people[1])
        people[0].name = "withname"
        people[0].age

        //А можно все это заменить следующим:
        //with перезапишет (изменит) сам people и запишет данные в withRes,
        // (!) но with не возвращает сам объект, т.е. нельзя написать так:
        //withRes.forEach { (it.print3()) }
        val withRes = with(people) {
            indexOf(get(0))
            indexOf(get(1))
            get(0).name =
                "withname1" //receiver - это тело передаваемого в обработку объекта, в данном случае это people. А block - это функции, которые применяются к этому телу. people - это receiver, а функция - это изменить имя get(0).name = "withname1"
            get(1).name = "withname2"
            get(0).age
        }

        people.forEach { (it.print3()) }

//apply
        //apply перезапишет (изменит) сам people и запишет данные в applyRes и возвращает сам объект
        val applyRes = people.apply {
            //people.apply { //Можно не объявлять новое свойство, и в данном случае изменится только people, т.к. не создана переменная, в которую также будут записаны новые данные
            indexOf(get(0))
            indexOf(get(1))
            get(0).name = "applyname1"
            get(1).name = "applyname2"
            get(0).age
        }


        people.forEach { (it.print3()) }
        applyRes.forEach { (it.print3()) }

//also
        val alsoRes = people.also {
            it.get(0).name = "alsoname1"
            it.get(1).name = "alsoname2"
        }
        alsoRes.forEach { (it.print3()) }

        var x = 20
        var y = 30
        x = y.also { y = x }
        //x = y.apply { y = x } //Будет тоже самое
        Log.d("MyLogsLambs2", "x = $x y = $y")

        var person: LambdaKotlin.Person? = null
        person = LambdaKotlin.Person("no-null", 12)
        if (person != null) {
            person.print3()
        }

//let
        //let - потокобезопасная функция, и позволяет сделать проверку на null
        person?.let {
            it.print3()
        }



        //00:10:00 Lesson5
        var field: String? = null

        //в отличие от if, let работает с it, а не с field напрямую
        field?.let { it }

        //Если написать так (т.е. обратиться к field напрямую), то это ломает смысл let, т.к. обращение напрямую к свойству лишает блок потокобезопасности, потому что одновременно к field из конкурентного потока также может поступить обращение на ее изменение, что повлечет неправильную работу приложения
        field?.let { it
            field
        }
        field?.let {
            field
        }

        //Работа let выглядит примерно так:
        //сначала делаем клон field
        var copyField = field/*.copy()*/
        //после этого в if работаем с этим клоном, и в конце возвращаем copyField обратно в field, т.е. мы на время нахождения в блоке нашего let мы работаем с it и оно блокируется для других потоков
        if (copyField != null) {
            copyField = "w"
            copyField = "w"
            copyField = "w"
            copyField = "w"
        }

        if (field != null) {
            field = "w"
            field = "w"
            field = "w"
            field = "w"
        }

//run
        var str1 = "str1"
        var res1 = str1.run {
            "str2"
        }
        var str2 = "str3"
        var res2 = run {
            str2 = "str4"
        }
    }
}

//2:54:40