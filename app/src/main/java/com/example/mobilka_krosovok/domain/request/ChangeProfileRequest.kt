package org.example.domain.request

class ChangeProfileRequest (
    val userId: Int,
    val lastname: String,
    val email: String,
    val phone: String,
    val address: String
)
