package io.cookma.recipeManagement.application.dtoModel

import io.cookma.recipeManagement.domain.model.Ingredient

data class RecipeDto(
        val name: String,
        val image: String,
        val effort: String,
        val category: String,
        val nutrition: List<String>,
        val preparationTime: Int,
        val restTime: Int,
        val ingredients: List<Ingredient>,
        val preparation: String,
        val user: String
)
