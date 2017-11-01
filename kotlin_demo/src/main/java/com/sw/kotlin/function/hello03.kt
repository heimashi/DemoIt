package com.sw.kotlin.function


/*
*集合
* */

fun test01() {
    val set = setOf("aa", "bb", "aa");
    println("" + set.javaClass + " " + set)

    val hashSet = hashSetOf("aa", "bb", "aa")
    println("" + hashSet.javaClass + " " + hashSet)

    val list = listOf(2, 4, 0)
    println("" + list.javaClass + " " + list)

    val arrayList = arrayListOf(3, 7, 1)
    println("" + arrayList.javaClass + " " + arrayList)

    val map = mapOf(1 to "11", 2 to "22")
    println("" + map.javaClass + " " + map)

    val hashMap = hashMapOf(1 to "11", 2 to "22")
    println("" + hashMap.javaClass + " " + hashMap)

}


/*
* 函数简化
* */

fun test02() {
    val list = listOf(2, 4, 0)
    println(list)
}


fun <T> joinToString(collection: Collection<T>,
                     separator: String,
                     prefix: String,
                     postfix: String): String {
    val result = StringBuilder(prefix)
    for ((index, element) in collection.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}

fun test03() {
    val list = listOf(2, 4, 0)
    println(joinToString(list, " - ", "[", "]"))
}