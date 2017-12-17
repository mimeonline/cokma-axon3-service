package de.cookma.recipeManagement.infrastructure.repository

import de.cookma.recipeManagement.application.viewModel.RecipeViewModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param

interface RecipeRepository : JpaRepository<RecipeViewModel, Long> {

    fun findByTitle(@Param("title") title: String): RecipeViewModel
    fun findByRecipeId(@Param("recipeId") title: String): RecipeViewModel
    fun deleteByRecipeId(@Param("recipeId") title: String)

}