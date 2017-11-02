package com.sw.kotlin.lambda

import android.content.Context
import android.widget.TextView

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


    var a = 0
    persons.forEach {
        a++
        println(it.name)
    }
}


/*
* lambda和with apply
* */
fun alphabet(): String {
    val result = StringBuilder()
    for (letter in 'A'..'Z') {
        result.append(letter)
    }
    result.append("\nEND")
    return result.toString()
}


fun test04() {
    println(alphabet())
}


fun alphabet2(): String {
    val result = StringBuilder()
    return with(result) {
        for (letter in 'A'..'Z') {
            this.append(letter)
        }
        append("\nEND")
        toString()
    }
}


fun alphabet3(): String {
    val result = StringBuilder()
    return with(result, {
        for (letter in 'A'..'Z') {
            append(letter)
        }
        append("\nEND")
        toString()
    })
}

fun alphabet4(): String {
    return with(StringBuilder()) {
        for (letter in 'A'..'Z') {
            append(letter)
        }
        append("\nEND")
        toString()
    }
}


fun alphabet5() = StringBuilder().apply {
    for (letter in 'A'..'Z') {
        append(letter)
    }
    append("\nEND")
}.toString()


fun createMyTextView(context: Context) =
        TextView(context).apply {
            text = "content"
            textSize = 20.0f
            setPadding(10,0,0,0)
        }
