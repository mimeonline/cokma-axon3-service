package de.cookma.usermanagement.domain.model

import java.time.LocalDateTime

data class UserProfileCreatedEvent(
        val userId: String,
        val creationDate: LocalDateTime,
        val username: String,
        val email: String,
        val firstname: String,
        val lastname: String
)

data class UserProfileDeletedEvent(val userId: String)