package org.example.domain

import org.example.domain.request.AuthorizeRequest
import org.example.domain.request.ChangePasswordRequest
import org.example.domain.request.ChangeProfileRequest
import org.example.domain.request.RegistrationRequest
import org.example.domain.response.UserResponse

interface UserUseCase {
    fun authorize(authorizeRequest: AuthorizeRequest): UserResponse
    fun registration(registrationRequest: RegistrationRequest): UserResponse
    fun changePassword(changePasswordRequest: ChangePasswordRequest)
    fun changeProfile(changeProfileRequest: ChangeProfileRequest): UserResponse
}