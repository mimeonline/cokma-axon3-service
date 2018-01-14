package de.cookma.recipeManagement.application.viewModel

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*


class RecipeViewModel(

        @Id
        @JsonIgnore
        var id: String? = null,
        var recipeId: String = "",
        var name: String = "",
        var effort: String = "",
        var category: String = "",
        var nutrition: List<String> = ArrayList(),
        var preparationTime: Int = 0,
        var restTime: Int = 0,
        var ingredient: String = "",
        var preparation: String = "") {

}