package com.sw.kotlin

import android.util.Log.println

/**
 * Created by shiwang on 29/10/2017.
 */
interface HelloInterface {

    val aa: String
    var bb: Int
    fun a(cc: String)
    //fun printT(): Int

    fun add(c: Int, d: Int): Int {
        return c + d
    }
}