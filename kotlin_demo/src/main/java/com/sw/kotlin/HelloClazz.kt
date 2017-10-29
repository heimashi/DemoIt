package com.sw.kotlin

/**
 * Created by shiwang on 29/10/2017.
 */
class HelloClazz : HelloAbstractClazz(), HelloInterface {
    override val aa = "1"
    override var bb = 3;
    override fun a(cc: String){

    }

//    override fun add(c: Int, d: Int): Int {
//        return c + d
//    }
}