package io.cookma.recipeManagement.infrastructure.repository

import io.cookma.recipeManagement.application.RecipeProjection
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.query.Param

interface RecipeRepository : MongoRepository<RecipeProjection, Long> {

    fun findByName(@Param("name") name: String): RecipeProjection
    fun findByRecipeId(@Param("recipeId") recipeId: String): RecipeProjection
    fun deleteByRecipeId(@Param("recipeId") recipeId: String)
    fun findAllByOrderByLastModificationDateDesc(): List<RecipeProjection>
}