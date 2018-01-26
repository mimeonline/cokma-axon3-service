package de.cookma.userprofilemanagement.domain.model

data class UserProfileCreatedEvent(
        val userId: String,
        val nickname: String,
        val email: String,
        val firstname: String,
        val lastname: String
)

data class UserProfileDeletedEvent(val userId: String)