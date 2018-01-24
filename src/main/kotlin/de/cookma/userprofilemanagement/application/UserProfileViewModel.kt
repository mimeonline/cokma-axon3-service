package de.cookma.userprofilemanagement.application

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class UserProfileViewModel(

        @Id
        @JsonIgnore
        var id: String? = null,
        var userId: String = "",
        var email: String = "",
        var nickname: String = "",
        var firstname: String = "",
        var lastname: String = ""
)