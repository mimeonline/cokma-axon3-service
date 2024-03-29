package io.cookma.recipeManagement.domain.model

import org.axonframework.serialization.Revision
import java.time.LocalDateTime

@Revision("1")
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
        val preparations: List<Preparation>)

@Revision("1")
data class RecipeUpdatedEvent(
        val recipeId: String,
        val name: String,
        val effort: String,
        val category: String,
        val nutrition: List<String>,
        val preparationTime: Int,
        val restTime: Int,
        val ingredients: List<Ingredient>,
        val preparations: List<Preparation>)

@Revision("1")
data class RecipeDeletedEvent(val recipeId: String, val image: EvtImage)

@Revision("1")
data class EvtImage(val imageId: String, val extension: String)