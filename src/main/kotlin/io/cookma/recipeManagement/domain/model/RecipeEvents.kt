package io.cookma.recipeManagement.domain.model

import java.time.LocalDateTime

data class RecipeCreatedEvent(
        val recipeId: String,
        val creationDate: LocalDateTime,
        val userProfileId: String,
        val name: String,
        val image: EvtImage,
        val effort: String,
        val category: String,
        val nutrition: List<String>,
        val preparationTime: Int,
        val restTime: Int,
        val ingredients: List<Ingredient>,
        val preparation: String)

data class RecipeUpdatedEvent(
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