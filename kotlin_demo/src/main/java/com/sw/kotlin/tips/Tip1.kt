package com.sw.kotlin.tips

/**
 * Created by shiwang on 14/12/2017.
 */
class Tip1 {

    val p1 by lazy {
        "ss lazy"
    }

    lateinit var p2: String

    fun test1() {
        println(p1)
    }
}

