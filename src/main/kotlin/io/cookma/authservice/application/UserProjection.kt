package io.cookma.authservice.application

import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "User")
class UserProjection(
        var userId: String = "",
        var userProfileId: String  = "",
        var username: String = "",
        var password: String = ""
)