package io.cookma.recipeManagement.application

import io.cookma.recipeManagement.domain.model.Ingredient
import io.cookma.recipeManagement.domain.model.Preparation

data class RecipeDto(
        val name: String,
        val image: String,
        val effort: String,
        val category: String,
        val nutrition: List<String>,
        val preparationTime: Int,
        val restTime: Int,
        val ingredients: List<Ingredient>,
        val preparations: List<Preparation>,
        val userId: String,
        val testField: String
)

data class RecipeEditDto(
        val name: String,
        val effort: String,
        val category: String,
        val nutrition: List<String>,
        val preparationTime: Int,
        val restTime: Int,
        val ingredients: List<Ingredient>,
        val preparations: List<Preparation>,
        val userId: String,
        val testField: String
)
