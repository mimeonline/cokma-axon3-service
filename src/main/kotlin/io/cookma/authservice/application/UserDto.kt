package io.cookma.authservice.application

data class UserDto (
        val userProfileId:String,
        val username: String,
        val password: String
)