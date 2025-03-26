package org.example.data

import org.example.data.model.UserDTO
import org.example.domain.request.RegistrationRequest

class UserRepositoryImpl: UserRepository {
    private val userSource: MutableList<UserDTO> = userList.toMutableList()


    override fun addUser(registrationRequest: RegistrationRequest): UserDTO {
        val newUser = UserDTO(
            userId = userSource.size + 1,
            email = registrationRequest.email,
            firstName = registrationRequest.firstName,
            password = registrationRequest.password
        )
        userSource.add(newUser)
        return newUser
    }

    override fun removeUserById(userId: Int): Boolean {
       return userSource.removeIf { it.userId == userId }
    }

    override fun updateUserById(userId: Int, updatedUser: UserDTO): UserDTO {
        val oldUser = userSource.firstOrNull { it.userId == userId }
        checkNotNull(oldUser)
        oldUser.phone = updatedUser.phone
        oldUser.address = updatedUser.address
        oldUser.firstName = updatedUser.firstName
        oldUser.lastName = updatedUser.lastName
        oldUser.password = updatedUser.password
        oldUser.email = updatedUser.email
        return oldUser
    }

    override fun getAllUsers(): List<UserDTO> = userSource

    override fun findUserById(userId: Int): UserDTO {
        val findUser = userSource.firstOrNull { it.userId == userId }
        checkNotNull(findUser)
        return findUser
    }
}