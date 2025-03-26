package org.example.ui

import org.example.domain.UserUseCase
import org.example.domain.request.AuthorizeRequest
import org.example.domain.request.ChangePasswordRequest
import org.example.domain.request.ChangeProfileRequest
import org.example.domain.request.RegistrationRequest
import org.example.domain.response.UserResponse

class UserUI(private val userUseCase: UserUseCase) {
    var userAuthorized: UserResponse? = null
    fun authorize(){
        println("Введите почту")
        val email = readlnOrNull()
        checkNotNull(email){
            "Почта не должна отсутствовать"
        }
        println("Введите пароль")
        val password = readlnOrNull()
        checkNotNull(password){
            "Пароль не должен отсутствовать"
        }
        val authorizeRequest = AuthorizeRequest(
            email = email,
            password = password,)
        val user = userUseCase.authorize(authorizeRequest)
        userAuthorized = user
        println(userResponseToString(user))
    }
    fun changePassword(){
        checkNotNull(userAuthorized){
            "Вы не авторизованы"
        }
        println("Введите пароль")
        val password = readlnOrNull()
        checkNotNull(password){
            "Пароль не должен отсутствовать"
        }
        val changePasswordRequest = ChangePasswordRequest(
            userId = userAuthorized!!.userId,
            newPassword = password
        )
        userUseCase.changePassword(changePasswordRequest)

    }
    fun registration(){
        println("Введите имя")
        val firstName = readlnOrNull()
        checkNotNull(firstName){
            "Имя не должно отсутствовать"
        }
        println("Введите почту")
        val email = readlnOrNull()
        checkNotNull(email){
            "Почта не должна отсутствовать"
        }
        println("Введите пароль")
        val password = readlnOrNull()
        checkNotNull(password){
            "Пароль не должен отсутствовать"
        }
        val registrationRequest = RegistrationRequest(
            email = email,
            firstName = firstName,
            password = password
        )
        val newUser = userUseCase.registration(registrationRequest)
        println(userResponseToString(newUser))
    }

    private fun userResponseToString(userResponse: UserResponse): String{
        val printOutput = StringBuilder()
        printOutput.append("Ваш id ${userResponse.userId}")
        printOutput.appendLine()
        printOutput.append("Ваша почта ${userResponse.email}")
        printOutput.appendLine()
        printOutput.append("Ваше имя ${userResponse.firstName}")
        printOutput.appendLine()
        printOutput.append("Ваша почта ${userResponse.email} ")
        if (!userResponse.lastName.isNullOrBlank()){
            printOutput.appendLine()
            printOutput.append("Ваше фамилия ${userResponse.lastName}")
        }
        if (!userResponse.phone.isNullOrBlank()){
            printOutput.appendLine()
            printOutput.append("Ваш телефон ${userResponse.phone}")
        }
        if (!userResponse.address.isNullOrBlank()){
            printOutput.appendLine()
            printOutput.append("Ваш адрес ${userResponse.address}")
        }
        return printOutput.toString()
    }

    fun changeProfile(){
        checkNotNull(userAuthorized){
            "Вы не авторизованы"
        }
        println("Введите новую почту")
        val email = readlnOrNull()
        println("Введите новый телефон")
        val phone = readlnOrNull()
        println("Введите новую фамилию")
        val lastname = readlnOrNull()
        println("Введите новый адрес")
        val address = readlnOrNull()
        val changeProfileRequest = ChangeProfileRequest(
            userId = userAuthorized!!.userId,
            email = email.toString(),
            phone = phone.toString(),
            address = address.toString(),
            lastname = lastname.toString()
        )

        val newProfileUser = userUseCase.changeProfile(changeProfileRequest)
        println(userResponseToString(newProfileUser))
        println("Профиль успешно изменен")
    }
}