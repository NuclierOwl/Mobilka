package org.example.domain.response

data class UserResponse(
    val userId: Int,
    var firstName: String,
    var lastName: String? = null,
    var email: String,
    var phone: String? = null,
    var address: String? = null,
)
