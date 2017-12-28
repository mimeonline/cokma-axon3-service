package de.cookma.recipeManagement.domain.model

import org.axonframework.commandhandling.TargetAggregateIdentifier

data class CreateRecipeCommand(
        val recipeId: String,
        val title: String,
        val subTitle: String,
        val shortDescription: String,
        val preparation: String)

data class UpdateRecipeCommand(
        @TargetAggregateIdentifier val recipeId: String,
        val title: String,
        val subTitle: String,
        val shortDescription: String,
        val preparation: String)

data class DeleteRecipeCommand(
        @TargetAggregateIdentifier val recipeId: String)