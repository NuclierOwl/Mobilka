package org.example.data

import org.example.data.model.UserDTO
import org.example.domain.request.RegistrationRequest

interface UserRepository {
    fun addUser(registrationRequest: RegistrationRequest): UserDTO
    fun removeUserById(userId: Int): Boolean
    fun updateUserById(userId: Int, updatedUser: UserDTO): UserDTO
    fun getAllUsers(): List<UserDTO>
    fun findUserById(userId: Int): UserDTO
}