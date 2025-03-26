package org.example.data.model

import org.w3c.dom.Text

data class SneakersDTO(
    val productId: Int,
    var productName: String,
    var cost: String,
    var count: Int,
    var photo: String,
    var description: Text

)