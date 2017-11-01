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


    println(joinToString(list, separator = " - ", prefix = "[", postfix = "]"))
}

fun <T> joinToString2(collection: Collection<T>,
                      separator: String = ", ",
                      prefix: String = "",
                      postfix: String = ""): String {
    val result = StringBuilder(prefix)
    for ((index, element) in collection.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}


fun test04() {
    val list = listOf(2, 4, 0)

    println(joinToString2(list, " - "))
}


/*
* 顶层函数和属性
* */
var a = 10
val b = 5


/*
* 扩展函数和属性
* */
fun String.lastChar(): Char = this.get(this.length - 1)

fun test5() {
    val str = "sfafsafz";
    println(str.lastChar())
}


fun <T> Collection<T>.joinToString3(separator: String = ", ",
                                    prefix: String = "",
                                    postfix: String = ""): String {
    val result = StringBuilder(prefix)
    for ((index, element) in withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}

fun test6() {
    val list = listOf(2, 4, 0)
    println(list.joinToString3("/"))
}


val String.lastChar: Char
    get() = get(length - 1)


var StringBuilder.lastChar: Char
    get() = get(length - 1)
    set(value: Char) {
        setCharAt(length - 1, value)
    }


/*
*可变参数 中缀调用
* */
fun test07(vararg values: String) {
    val list = listOf("aaa", *values)
    println(list)
}


fun test08() {
    val hashMap = hashMapOf(1 to "11", 2 to "22", 4.to("44"))
    println("" + hashMap.javaClass + " " + hashMap)

}

infix fun Any.to(other: Any) = Pair(this, other)

infix fun Int.add(other: Int) = this + other

fun test09() {
    println(4 add 8)
    val (number, name) = 1 to "one"
    println("n:$number $name")
    println("""
        hsfshfsfhfas
        sffsf
        "sfsf"
        \fsfs
        """)
}


/*
*局部函数和扩展
* */
class User(val id: Int, val name: String, val address: String, val email: String)

fun saveUser(user: User) {
    if (user.name.isEmpty()) {
        throw IllegalArgumentException("Can't save user ${user.id}: empty Name")
    }
    if (user.address.isEmpty()) {
        throw IllegalArgumentException("Can't save user ${user.id}: empty Address")
    }
    if (user.email.isEmpty()) {
        throw IllegalArgumentException("Can't save user ${user.id}: empty Email")
    }
    //save to db ...
}

fun saveUser2(user: User) {
    fun validate(value: String, fildName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException("Can't save user ${user.id}: empty $fildName")
        }
    }

    validate(user.name, "Name")
    validate(user.address, "Address")
    validate(user.email, "Email")
    //save to db ...
}


//扩展函数抽取逻辑
fun User.validateAll(){
    fun validate(value: String, fildName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException("Can't save user $id: empty $fildName")
        }
    }

    validate(name, "Name")
    validate(address, "Address")
    validate(email, "Email")
}

fun saveUser3(user: User){
    user.validateAll()
    //save to db ...
}








