package org.example.domain

import org.example.data.model.UserDTO
import org.example.domain.response.UserResponse

fun UserDtoToUserResponse(userDTO: UserDTO) = UserResponse(
    lastName = userDTO.lastName,
    firstName = userDTO.firstName,
    email = userDTO.email,
    phone = userDTO.phone,
    userId = userDTO.userId,
    address = userDTO.address,
)