package org.example.domain

import org.example.data.UserRepository
import org.example.domain.request.AuthorizeRequest
import org.example.domain.request.ChangePasswordRequest
import org.example.domain.request.ChangeProfileRequest
import org.example.domain.request.RegistrationRequest
import org.example.domain.response.UserResponse

class UserUseCaseImpl(private val userRepository: UserRepository): UserUseCase {
    override fun authorize(authorizeRequest: AuthorizeRequest): UserResponse {
        val findUser = userRepository.getAllUsers()
            .firstOrNull {
                it.email == authorizeRequest.email
            }
        checkNotNull(findUser){
            "Пользователь с такой почтой не найден"
        }
        require(findUser.password == authorizeRequest.password){
            "Пароли не совпадают"
        }
        return UserDtoToUserResponse(findUser)
    }

    override fun registration(registrationRequest: RegistrationRequest): UserResponse {
        //уникальность почты
        val isUnique = userRepository.getAllUsers().firstOrNull{
            it.email == registrationRequest.email
        } == null
        require(isUnique){
            "Такая почта уже существует"
        }
        val newUser = userRepository.addUser(registrationRequest)
        return UserDtoToUserResponse(newUser)
    }

    override fun changePassword(changePasswordRequest: ChangePasswordRequest) {
        val updatedUser = userRepository.findUserById(changePasswordRequest.userId)
        updatedUser.password = changePasswordRequest.newPassword
        userRepository.updateUserById(changePasswordRequest.userId, updatedUser)
    }




    override fun changeProfile(changeProfileRequest: ChangeProfileRequest): UserResponse {
        val userProfile = userRepository.findUserById(changeProfileRequest.userId)
        checkNotNull(userProfile){"Пользователь не найден"}
        userProfile.apply {
            phone = changeProfileRequest.phone
            address = changeProfileRequest.address
            lastName = changeProfileRequest.lastname
            email = changeProfileRequest.email
        }

        userRepository.updateUserById(changeProfileRequest.userId,userProfile)




        return UserDtoToUserResponse(userProfile)

    }
}