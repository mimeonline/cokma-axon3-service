package de.cookma.authservice.application

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.security.core.userdetails.UserDetails

@Document
class UserViewModel(
        var userId: String = "",
        var userProfileId: String  = "",
        var username: String = "",
        var password: String = ""
)