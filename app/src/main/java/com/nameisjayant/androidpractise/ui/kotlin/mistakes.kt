package com.nameisjayant.androidpractise.ui.kotlin

import android.util.Patterns
import java.io.IOException


fun <T> Boolean.then(value: T): T? = if (this) value else null


fun main() {
   val value = false.then("Valid input")
    print(value)

    val num: Int? = 1

    num.isNullOrZero()

    val lists: List<Int>? = listOf(1,2,3,4,3,3,3,33)
    if(lists.isNullOrEmpty()){

    }
}


fun Int?.isNullOrZero(): Boolean = this == null || this == 0
