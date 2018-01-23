package de.cookma.recipeManagement.domain.model

data class RecipeCreatedEvent(
        val recipeId: String,
        val name: String,
        val image: EvtImage,
        val effort: String,
        val category: String,
        val nutrition: List<String>,
        val preparationTime: Int,
        val restTime: Int,
        val ingredients: List<Ingredient>,
        val preparation: String)

data class RecipeUpdateEvent(
        val recipeId: String,
        val name: String,
        val effort: String,
        val category: String,
        val nutrition: List<String>,
        val preparationTime: Int,
        val restTime: Int,
        val ingredients: List<Ingredient>,
        val preparation: String)

data class RecipeDeletedEvent(val recipeId: String, val image: EvtImage)

data class EvtImage(val imageId: String, val extension: String)