package io.cookma.authservice.domain.model

import org.axonframework.serialization.Revision

@Revision("1")
data class UserCreatedEvent(val userId: String, val userProfileId: String, val username: String, val password: String)