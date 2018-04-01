package io.cookma.usermanagement.domain.model

import org.axonframework.serialization.Revision
import java.time.LocalDateTime

@Revision("1.0")
data class UserProfileCreatedEvent(
        val userId: String,
        val creationDate: LocalDateTime,
        val username: String,
        val email: String,
        val firstname: String,
        val lastname: String
)

@Revision("1.0")
data class UserProfileDeletedEvent(val userId: String)