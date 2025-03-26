package org.example.domain.request

data class RegistrationRequest (
    val email: String,
    val password: String,
    val firstName: String
)