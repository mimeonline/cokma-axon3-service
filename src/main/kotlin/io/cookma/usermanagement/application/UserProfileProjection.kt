package io.cookma.usermanagement.application

import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "UserProfile")
class UserProfileProjection(
        var userId: String = "",
        var email: String = "",
        var username: String = "",
        var firstname: String = "",
        var lastname: String = ""
)