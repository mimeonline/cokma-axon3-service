package io.cookma.authservice.domain.model

data class CreateUserCommand(val userId: String, val userProfileId: String, val username: String, val password:String)
