package de.cookma.recipeManagement.domain.model

data class CreateRecipeCommand(val recipeId: String, val title: String, val subTitle: String, val preparation: String)
