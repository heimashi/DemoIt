package com.sw.kotlin.type

/*
* 可空性
* */
fun test01() {
    var a: String = "aa"
    //a = null
    var b: String? = "bb"
    b = null
}

fun strLen(s: String): Int {
    return s.length
}

fun strLen2(s: String?): Int {
    //s.length
    if (s != null) {
        return s.length
    }
    return 0
}

fun strLen3(s: String?): Int? {
    return s?.length
}

fun strLen4(s: String?): Int {
    return s?.length ?: 0
}


fun test02() {
    val i = 10
    val p = i as? String
    println(p)
}


fun strLen5(s: String?): Int {
    return s!!.length
}


/*
* 基本数据类型
* */
fun test03() {
    val a = 20
    val b = 20L
    val c = 'c'
    val d = true

    println((20.coerceIn(0, 100)))


    //val e:Long = a
    val f: Long = a.toLong()
    //val g:Int = b
    val h: Int = b.toInt()


}


/*
* 其他基本类型  Any  Any?  Unit  Nothing
* */
fun test04() {
    val a = 40;
    println(a is Any)
    println(a is Any?)
    println(Unit is Any)

    val b: Any = 'c'
    println(b is Any?)

    val e: Any? = 'c'
    println(e is Any)

    println(null is Any)

    println(null is Any?)

}

fun test05(): Unit {

}

interface Processor<T> {
    fun process(): T
}

class NoResultProcessor : Processor<Unit> {
    override fun process() {
        //
        //return
    }
}


fun test06() {
    val e: Exception = Exception("aaa")
    val t: Throwable = Throwable("bbb")
    println(e is Nothing)
    println(t is Nothing)
    println(t is Any)
    println(t is Any?)
}


fun fail(message: String): Nothing {
    throw IllegalStateException(message)
}

fun test07() {
    fail("hahaha")
}


/*
* 运算符重载
* */
class Point(val x: Int, val y: Int) {
    operator fun plus(other: Point): Point {
        return Point(x + other.x, y + other.y)
    }

    override fun toString(): String {
        return "[x:$x, y:$y]"
    }

    operator fun minus(other: Point): Point {
        return Point(x - other.x, y - other.y)
    }

}


fun test08() {
    val point1 = Point(10, 10)
    val point2 = Point(4, 4)
    println(point1 + point2)
    println(point1 - point2)
}




