package io.cookma.recipeManagement.application.viewModel

import com.fasterxml.jackson.annotation.JsonIgnore
import io.cookma.recipeManagement.domain.model.Ingredient
import io.cookma.recipeManagement.domain.model.Preparation
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
class RecipeViewModel(

        @Id
        @JsonIgnore
        var id: String? = null,
        var recipeId: String = "",
        var lastModificationDate: LocalDateTime? = null,
        var userProfileId: String = "",
        var username: String = "",
        var name: String = "",
        var imageUrl: String = "",
        var effort: String = "",
        var category: String = "",
        var nutrition: List<String> = listOf(),
        var preparationTime: Int = 0,
        var restTime: Int = 0,
        var ingredients: List<Ingredient> = listOf(),
        var preparations: List<Preparation> = listOf(),
        var testField: String) {

}
