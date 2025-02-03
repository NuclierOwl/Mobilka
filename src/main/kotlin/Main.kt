package org.example

import org.example.data.UserRepository
import org.example.data.UserRepositoryImpl
import org.example.domain.UserUseCaseImpl
import org.example.ui.UserUI

fun main() {
    val userRepository = UserRepositoryImpl()
    val userUseCase = UserUseCaseImpl(userRepository)
    val userUI = UserUI(userUseCase)
    val SnikerRep: SnikerRep = SnikerRepImpl()

    val sneakersRepository: SneakersRepository = SneakersRepositoryImpl()
    val shoesUseCase: ShoesUseCase = ShoesUseCaseImpl(sneakersRepository)

    val allSneakers = SnikerRep.getAllSneakers()
    val sneakerById = SnikerRep.getSneakerById(1)

    val filteredSneakers = SnikerRep.filterSneakers { it.cost.toInt() > 160 } // фильтрация

    SnikerRep.deleteSneaker(2) // удоление
}