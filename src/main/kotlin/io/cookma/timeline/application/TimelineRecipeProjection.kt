package io.cookma.timeline.application

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "TimelineRecipe")
class TimelineRecipeProjection(

        @Id
        @JsonIgnore
        var id: String? = null,
        var recipeId: String = "",
        var lastModificationDate: LocalDateTime? = null,
        var name: String = "",
        var imageUrl: String = "",
        var username: String = ""
)