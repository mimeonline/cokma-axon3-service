package de.cookma.recipeManagement.domain.model

import org.axonframework.commandhandling.TargetAggregateIdentifier

data class CreateRecipeCommand(
        val recipeId: String,
        val userProfileId: String,
        val name: String,
        val image: CmdImage,
        val effort: String,
        val category: String,
        val nutrition: List<String>,
        val preparationTime: Int,
        val restTime: Int,
        val ingredients: List<Ingredient>,
        val preparation: String)

data class CmdImage(val imageId: String, val extension: String)


data class UpdateRecipeCommand(
        @TargetAggregateIdentifier val recipeId: String,
        val name: String,
        val effort: String,
        val category: String,
        val nutrition: List<String>,
        val preparationTime: Int,
        val restTime: Int,
        val ingredients: List<Ingredient>,
        val preparation: String)

data class DeleteRecipeCommand(@TargetAggregateIdentifier val recipeId: String)