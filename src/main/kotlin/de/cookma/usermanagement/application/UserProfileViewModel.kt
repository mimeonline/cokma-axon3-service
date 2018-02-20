package de.cookma.usermanagement.application

import org.springframework.data.mongodb.core.mapping.Document

@Document
class UserProfileViewModel(
        var userId: String = "",
        var email: String = "",
        var username: String = "",
        var firstname: String = "",
        var lastname: String = ""
)