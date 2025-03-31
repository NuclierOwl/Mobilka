package org.example.data

import org.example.data.model.SneakersDTO
import org.example.data.model.UserDTO
import org.example.domain.request.RegistrationRequest

interface SnikerRep {
    fun getAllSneakers(): List<SneakersDTO>
    fun getSneakerById(productId: Int): SneakersDTO?
    fun addSneaker(sneaker: SneakersDTO)
    fun updateSneaker(sneaker: SneakersDTO)
    fun deleteSneaker(productId: Int)
    fun filterSneakers(filter: (SneakersDTO) -> Boolean): List<SneakersDTO>
}