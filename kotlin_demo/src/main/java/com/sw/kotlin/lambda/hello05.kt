package com.sw.kotlin.lambda

/*
* lambda和成员引用
* */
interface OnClickListener {
    fun onClick()
}

class View {
    var listener: OnClickListener? = null;
    fun setOnClickListener(listener: OnClickListener) {
        this.listener = listener
    }

    fun setOnClickListener(listener: () -> Unit) {

    }
}

fun test01() {
    val view = View()
    view.setOnClickListener(object : OnClickListener {
        override fun onClick() {
            println("aaa")
        }
    })
}

fun test02() {
    val view = View()
    view.setOnClickListener {
        println()
    }
}


class Person(val name: String, val age: Int)

fun test03() {
    val persons = listOf(Person("jack", 13), Person("bob", 24))
    println(persons.maxBy { it.age })
    println(persons.maxBy(Person::age))
    run { println("haha") }

    println(persons.maxBy({ p: Person -> p.age }))
    println(persons.maxBy() { p: Person -> p.age })
    println(persons.maxBy { p: Person -> p.age })


    var a =0
    persons.forEach{
        a++
        println(it.name)
    }
}

