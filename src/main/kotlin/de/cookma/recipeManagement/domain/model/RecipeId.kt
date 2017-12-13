package de.cookma.recipeManagement.domain.model

data class RecipeId(val id: String)

fun createRecipeId() = "1"
//RecipeId(UUID.randomUUID().toString()) TODO Wieder zurückbauen
