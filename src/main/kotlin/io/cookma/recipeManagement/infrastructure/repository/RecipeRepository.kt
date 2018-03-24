package io.cookma.recipeManagement.infrastructure.repository

import io.cookma.recipeManagement.application.viewModel.RecipeViewModel
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.query.Param

interface RecipeRepository : MongoRepository<RecipeViewModel, Long> {

    fun findByName(@Param("name") name: String): RecipeViewModel
    fun findByRecipeId(@Param("recipeId") recipeId: String): RecipeViewModel
    fun deleteByRecipeId(@Param("recipeId") recipeId: String)
    fun findAllByOrderByLastModificationDateDesc(): List<RecipeViewModel>
}