package de.cookma.recipeManagement.application.dtoModel

// TODO kann bestimmt auch als data class integriert werden
class RecipeDto(
        var name: String,
        var effort: String,
        var category: String,
        var nutrition: List<String>,
        var preparationTime: Int,
        var restTime: Int,
        var ingredient: String,
        var preparation: String
)
