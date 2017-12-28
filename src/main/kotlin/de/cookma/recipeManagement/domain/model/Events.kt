package de.cookma.recipeManagement.domain.model

data class RecipeCreatedEvent(
        val recipeId: String,
        val title: String,
        val subTitle: String,
        val shortDescription: String,
        val preparation: String)

data class RecipeUpdateEvent(
        val recipeId: String,
        val title: String,
        val subTitle: String,
        val shortDescription: String,
        val preparation: String)

data class RecipeDeletedEvent(val recipeId: String)