package org.example.data.model

data class UserDTO(
    val userId: Int,
    var firstName: String,
    var lastName: String? = null,
    var password: String,
    var email: String,
    var phone: String? = null,
    var address: String? = null,
)
