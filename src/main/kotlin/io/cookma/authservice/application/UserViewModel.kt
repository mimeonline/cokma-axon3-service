package io.cookma.authservice.application

import org.springframework.data.mongodb.core.mapping.Document

@Document
class UserViewModel(
        var userId: String = "",
        var userProfileId: String  = "",
        var username: String = "",
        var password: String = ""
)