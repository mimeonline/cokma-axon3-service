package de.cookma.recipeManagement.application.viewModel

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.mongodb.core.mapping.Document
import javax.persistence.*

@Document
class RecipeViewModel(

        @Id
        @JsonIgnore
        var id: String? = null,
        var recipeId: String = "",
        var name: String = "",
        var effort: String = "",
        var category: String = "",
        var nutrition: List<String> = listOf(),
        var preparationTime: Int = 0,
        var restTime: Int = 0,
        var ingredient: String = "",
        var preparation: String = "") {

}
