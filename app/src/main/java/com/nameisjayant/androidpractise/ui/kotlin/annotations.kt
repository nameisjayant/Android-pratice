package com.nameisjayant.androidpractise.ui.kotlin


fun main() {
    oldFunction()
}

@Deprecated(
    "use newFunction() instead of",
    replaceWith = ReplaceWith("newFunction()"),
    level = DeprecationLevel.WARNING
)
fun oldFunction() {
    print("old function")
}

fun newFunction() {
    print("new function")
}

class Users(
    val name: String,
    val age: Int
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Users

        if (name != other.name) return false
        if (age != other.age) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + age
        return result
    }

    override fun toString(): String {
        return "Users(name='$name', age=$age)"
    }

}