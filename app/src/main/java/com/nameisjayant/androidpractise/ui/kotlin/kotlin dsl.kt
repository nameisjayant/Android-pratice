package com.nameisjayant.androidpractise.ui.kotlin


data class User(
    val name: String,
    val email: String,
    val address: Address
)

data class Address(
    val city: String,
    val zip: String
)

class UserBuilder {
    var name: String = ""
    var email: String = ""
    private var address: Address = Address("", "")

    fun build() = User(name, email, address)

    fun address(block: AddressBuilder.() -> Unit) {
        address = AddressBuilder().apply(block).build()
    }
}

class AddressBuilder {
    var city: String = ""
    var zip: String = ""

    fun build() = Address(city, zip)
}

fun user(block: UserBuilder.() -> Unit): User {
    return UserBuilder().apply(block).build()
}

fun main() {
    val user = user {
        name = "Jayant"
        email = "jayant@antino.com"
        address {
            city = "New Delhi"
            zip = "110033"
        }
    }
    print(user)
}