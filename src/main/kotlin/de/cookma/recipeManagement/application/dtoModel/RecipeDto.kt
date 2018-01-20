package de.cookma.recipeManagement.application.dtoModel

import de.cookma.recipeManagement.domain.model.Ingredient

data class RecipeDto(
        val name: String,
        val effort: String,
        val category: String,
        val nutrition: List<String>,
        val preparationTime: Int,
        val restTime: Int,
        val ingredients: List<Ingredient>,
        val preparation: String
)
