package com.sw.kotlin.hello


data class Person(val name: String, val age: Int? = null)

/*
* 与java不同：数组就是类，没有声明数组类型的特殊语法
* */
fun main(args: Array<String>) {
    val persons = listOf(Person("kotlin"), Person("java", age = 10))
    val oldest = persons.maxBy { it.age ?: 0 }
    println("The oldest is: $oldest")
}