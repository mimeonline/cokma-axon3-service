package de.cookma.userprofilemanagement.domain.model

import org.axonframework.commandhandling.TargetAggregateIdentifier

data class CreateUserProfileCommand(
        val userId: String,
        val nickname: String,
        val email: String,
        val firstname: String,
        val lastname: String
)

data class DeleteUserProfileCommand(@TargetAggregateIdentifier val userId: String)