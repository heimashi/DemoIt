package com.sw.kotlin.clazz

/*
* 接口
* */
interface Clickable {
    fun click()
    fun showSelf() = println("I'm clickable")
}

class Button : Clickable {
    override fun click() {
        println("haha")
    }

}


interface Focusable {
    fun showSelf() = println("I'm focusable")
}

class Button2 : Clickable, Focusable {
    override fun click() {
        println("haha")
    }

    override fun showSelf() {//必须实现
        super<Clickable>.showSelf()
        super<Focusable>.showSelf()
    }

}


/*
* final open abstract
* */
open class Button3 : Clickable {
    override fun click() {

    }

    fun aa() {

    }

    open fun bb() {

    }

}


abstract class Button4 : Clickable {

    abstract fun cc()

    override fun click() {

    }

    fun aa() {

    }

    open fun bb() {

    }

}


/*
* 内部类和嵌套类
* */
class Outer(val a: Int) {
    class Nested {
//        fun test(){
//            print(a)
//        }
    }

    inner class Inner {
        fun test() {
            print(a)
        }

        fun getOuterReference(): Outer = this@Outer
    }
}


sealed class Expr {
    class Num(val value: Int) : Expr()
    class Sum(val left: Expr, val right: Expr) : Expr()
}

fun eval(e: Expr): Int =
        when (e) {
            is Expr.Num -> e.value
            is Expr.Sum -> eval(e.right) + eval(e.left)
        }

class A {
    constructor(a: Int) {

    }
}


/*
*数据类和类委托
* */
data class Client(val name: String, val email: String)

interface Haha {
    fun a()
    fun b()
}

class AA : Haha {
    override fun a() {
        println("a")
    }

    override fun b() {
        println("b")
    }
}

class DelegatingAA(val a: AA) : Haha {
    override fun a() {
        a.a()
    }

    override fun b() {
        a.b()
    }

}

class DelegatingAA2(a: AA = AA()) : Haha by a {

}



