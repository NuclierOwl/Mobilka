package org.example.data.model

import org.w3c.dom.Text

data class NotificationDTO (
    val notificationId: Int,
    val userId: Int,
    var notificationText: Text,


)