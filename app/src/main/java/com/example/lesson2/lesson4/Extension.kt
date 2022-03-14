package com.example.lesson2.lesson4

import android.util.Log

//2:00:00
class Extension {
    fun main() {
        //2:01:00 функция With
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

        val alsoRes = people.also {
            it.get(0).name = "alsoname1"
            it.get(1).name = "alsoname2"
        }
        alsoRes.forEach { (it.print3()) }

        var x = 20
        var y = 30
        x = y.also { y = x }
//        x = y.apply { y = x } //Будет тоже самое
        Log.d("MyLogsLambs2", "x = $x y = $y")

        var person: LambdaKotlin.Person? = null
        person = LambdaKotlin.Person("no-null", 12)
        if (person != null) {
            person.print3()
        }

        //let - потокобезопасная функция, и позволяет сделать проверку на null
        person?.let {
            it.print3()
        }
    }
}

//2:54:40