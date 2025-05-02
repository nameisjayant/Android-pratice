package com.nameisjayant.androidpractise.ui.kotlin

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
fun String?.isNotNullOrEmpty(): Boolean {
    contract {
        returns(true) implies (this@isNotNullOrEmpty != null)
    }
    return this != null && this.isNotEmpty()
}

fun doSomething(value: String?) {
    if (value.isNotNullOrEmpty()) {
        println(value.length)
    }
}