package org.example.domain.request

data class AuthorizeRequest(
    val email: String,
    val password: String
)
