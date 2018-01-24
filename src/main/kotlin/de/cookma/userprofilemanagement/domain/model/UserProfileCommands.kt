package de.cookma.userprofilemanagement.domain.model

data class UserProfileCreateCommand(
        val userId: String,
        val nickname: String,
        val email: String,
        val firstname: String,
        val lastname: String
)