package com.sw.kotlin

/**
 * Created by shiwang on 29/10/2017.
 */
abstract class HelloAbstractClazz {

    abstract val aa: String
    abstract var bb: Int
    abstract fun a(cc: String)

    fun printT(): Int {
        return 3
    }

//    fun add(c: Int, d: Int): Int {
//        return c + d
//    }
}