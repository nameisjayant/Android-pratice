package com.nameisjayant.androidpractise.ui.kotlin

import kotlin.properties.Delegates
import kotlin.reflect.KProperty


class DelegateProperty {
}

class Rectangle(var width: Int, var height: Int) {
    val area: Int = width * height
}

fun main() {
//    val rect = Rectangle(5, 10)
//    println("Initial Area: ${rect.area}") // Output: 50
//    rect.width = 7
//    println("Updated Area: ${rect.area}") // Output: 50
    val user = UserProfile()
    user.name = "Alice"  // Triggers change notification
    user.name = "Bob"
    val printer = Printer1 {

    }
}

class UserProfile {
    var name: String by Delegates.observable("Unknown") { _, old, new ->
        println("Name changed from $old to $new")
    }
}

class LoggingDelegates {
    private var value: String = ""

    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return value
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, newValue: String) {
        value = newValue
    }

}



fun interface Printer1{
    fun print()
}

data class Test(val id:Int){
    companion object{

    }
}

