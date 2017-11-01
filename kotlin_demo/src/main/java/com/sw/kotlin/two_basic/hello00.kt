package com.sw.kotlin.two_basic

import java.io.BufferedReader

/**
 * 方法 if是表达式
 */
fun max(a: Int, b: Int): Int {
    return if (a > b) a else b
}


fun max2(a: Int, b: Int): Int = if (a > b) a else b


fun max3(a: Int, b: Int) = if (a > b) a else b


/*
* 变量
* */
val a1: String = "hello"

val a2 = "hello"

val a3 = 100

var a4: String = "hello"


/*
* 字符串模版
* */
fun aa(args: Array<String>) {
    val a = "kotlin"
    println("hello $a")
    println("hello ${if (args.size > 1) args[1] else "nothing"}")
}


/*
* 类
* */
class Person(val name: String, var isMarried: Boolean)


fun test1() {
    val person = Person("jack", true)
    println(person.name)
}


class Rectangle(val width: Int, val height: Int) {
    val isSquare: Boolean
        get() {
            return height == width
        }
}


class Rectangle2(val width: Int, val height: Int) {
    val isSquare: Boolean
        get() = height == width
}


/*
* 枚举
* */
enum class Color {
    RED, ORANGE, YELLOW
}


enum class Color2(val r: Int, val g: Int, val b: Int) {
    RED(255, 0, 0), ORANGE(255, 165, 0), YELLOW(255, 255, 0); //必须有分号

    fun rgb() = r * 256 * 256 + g * 256 + b
}


/*
* when  if
* */
fun test02(color: Color) =
        when (color) {
            Color.RED -> "red color"
            Color.ORANGE -> "orange color"
            Color.YELLOW -> "yellow color"
        }


fun test03(color: Color) =
        when (color) {
            Color.RED, Color.ORANGE -> "good color"
            Color.YELLOW -> "bad color"
        }


fun test04(c1: Color, c2: Color) =
        when (setOf(c1, c2)) {
            setOf(Color.RED, Color.ORANGE) -> "good color"
            else -> "bad color"
        }

fun test05(): String {
    return when {
        2 in 1..10 -> "good"
        4 is Int -> "haha"
        else -> "bad"
    }
}

/*
* 类型智能转换 as is
* */
interface Expr

class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

fun eval(e: Expr): Int {
    if (e is Num) {
        val n = e as Num
        return n.value
    }
    if (e is Sum) {
        return eval(e.left) + eval(e.right)
    }
    throw IllegalArgumentException("error expr")
}

fun test06() {
    println(eval(Sum(Sum(Num(1), Num(2)), Num(4))))
}


fun eval2(e: Expr): Int =
        when (e) {
            is Num -> e.value
            is Sum -> eval2(e.left) + eval2(e.right)
            else -> throw IllegalArgumentException("error expr")
        }


fun eval3(e: Expr): Int =
        when (e) {
            is Num -> {
                println("hello when")
                e.value
            }
            is Sum -> eval3(e.left) + eval3(e.right)
            else -> throw IllegalArgumentException("error expr")
        }


/*
* while for
* */
fun testWhile() {
    var i = 0
    while (i < 10) {
        println(i++)
    }

    do {
        println(i++)
    } while (i < 15)
}

fun testFor() {
    for (i in 1..10) {
        print(i)
    }

    for (i in 10 downTo 1 step 2) {
        print(i)
    }


    val map = HashMap<String, String>()
    map.put("aa", "11")
    map.put("bb", "22")
    map.put("cc", "33")

    for ((k, v) in map) {
        print("key:$k  value:$v")
    }


    val list = arrayListOf("10", "22", "cc")
    for ((index, v) in list.withIndex()) {
        print("key:$index  value:$v")
    }

}


/*
* 异常
* */
fun testExc(i: Int) {
    if (i !in 1..100) {
        throw IllegalArgumentException("error input i")
    }
}


fun readNumber(reader: BufferedReader): Int? {
    try {
        val line = reader.readLine()
        return Integer.parseInt(line)
    } catch (e: NumberFormatException) {
        return null
    } finally {
        reader.close()
    }
}

fun readNumber2(reader: BufferedReader) {
    val num = try {
        val line = reader.readLine()
        Integer.parseInt(line)
    } catch (e: NumberFormatException) {
        null
    }
    println(num)
}








