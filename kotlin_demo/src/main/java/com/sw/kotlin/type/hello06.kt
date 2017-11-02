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


fun test02(){
    val i = 10
    val p = i as? String
    println(p)
}


fun strLen5(s: String?): Int {
    return s!!.length
}