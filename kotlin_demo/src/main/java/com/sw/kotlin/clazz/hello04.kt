package com.sw.kotlin.clazz

import android.widget.TextView

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


/*
* object
* */
object CC {
    val d = "str"
    fun e() {

    }
}

fun testObject() {
    CC.d
    CC.e()
}

class DD {
    companion object {
        fun aa() {

        }
    }
}

class Book private constructor(val bookName: String) {
    companion object {
        fun newABook(aa: String) = Book(aa)
        fun newBBook(bb: String) = Book(bb)
    }

}

fun testCompanion() {
    DD.aa()
    val bookA = Book.newABook("aaa")
    val bookB = Book.newBBook("bbb")
}

interface OnClickListener {
    fun onClick()
}

class View {
    var listener: OnClickListener? = null;
    fun setOnClickListener(listener: OnClickListener?) {
        this.listener = listener
    }
}

fun test10() {
    val view = View()
    var i = 0
    view.setOnClickListener(object : OnClickListener {
        override fun onClick() {
            println("aaa")
            i++
        }
    })
}