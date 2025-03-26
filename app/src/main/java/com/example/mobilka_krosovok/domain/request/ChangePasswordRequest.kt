package org.example.domain.request

data class ChangePasswordRequest(
    val userId: Int,
    val newPassword: String
)