package de.cookma.recipeManagement.domain.model

data class RecipeCreatedEvent(val recipeId: String, val title: String, val subTitle: String, val preparation: String)