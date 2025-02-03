package org.example.data.model

import java.sql.Timestamp

data class OrderDTO (
    val order_id: Int,
    var order_sum: Double,
    var delivery_cost: Double,
    var order_date: Timestamp

)