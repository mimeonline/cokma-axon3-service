package io.cookma.recipeManagement.domain.model

import java.util.*

data class RecipeId(val id: String)

fun createRecipeId() = RecipeId(UUID.randomUUID().toString())