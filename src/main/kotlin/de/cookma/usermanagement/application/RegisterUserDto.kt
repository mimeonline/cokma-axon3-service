package de.cookma.usermanagement.application

data class RegisterUserDto(
        val username: String,
        val email: String,
        val password: String,
        val nickname: String = "",
        val firstname: String = "",
        val lastname: String = "")