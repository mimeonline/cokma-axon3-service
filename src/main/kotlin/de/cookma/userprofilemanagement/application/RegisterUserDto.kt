package de.cookma.userprofilemanagement.application

data class RegisterUserDto(
        val username: String,
        val password: String,
        val nickname: String,
        val email: String,
        val firstname: String,
        val lastname: String)