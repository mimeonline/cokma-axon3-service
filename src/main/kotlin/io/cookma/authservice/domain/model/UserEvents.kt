package io.cookma.authservice.domain.model

data class UserCreatedEvent(val userId: String, val userProfileId: String, val username: String, val password: String)