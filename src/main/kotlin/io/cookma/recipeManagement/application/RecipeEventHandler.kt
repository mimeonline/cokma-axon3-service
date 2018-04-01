package io.cookma.recipeManagement.application

import io.cookma.recipeManagement.domain.model.RecipeCreatedEvent
import io.cookma.recipeManagement.domain.model.RecipeDeletedEvent
import io.cookma.recipeManagement.domain.model.RecipeUpdatedEvent
import io.cookma.recipeManagement.infrastructure.repository.RecipeRepository
import io.cookma.recipeManagement.infrastructure.store.RecipeImageStore
import io.cookma.usermanagement.infrastructure.repository.UserProfileRepository
import org.axonframework.config.ProcessingGroup
import org.axonframework.eventhandling.EventHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * Das Image Extension muß aus dem dataUri ausgelesen werden
 */
@Component
@ProcessingGroup("recipemanagement")
class RecipeEventHandler {

    @Autowired
    lateinit var recipeRepository: RecipeRepository

    @Autowired
    lateinit var recipeImageStore: RecipeImageStore

    @Autowired
    lateinit var userProfileRepository: UserProfileRepository

    @EventHandler
    fun handle(evt: RecipeCreatedEvent) {
        val imageUrl = "/images/" + evt.image.imageId + "." + evt.image.extension
        val userProfile = userProfileRepository.findByUserId(evt.userProfileId)
        recipeRepository.save(RecipeProjection(
                null,
                evt.recipeId,
                evt.creationDate,
                evt.userProfileId,
                userProfile.username,
                evt.name,
                imageUrl,
                evt.effort,
                evt.category,
                evt.nutrition,
                evt.preparationTime,
                evt.restTime,
                evt.ingredients,
                evt.preparations,
                evt.testField))
    }

    @EventHandler
    fun handle(evt: RecipeUpdatedEvent) {
        var recipe: RecipeProjection = recipeRepository.findByRecipeId(evt.recipeId)
        recipe.name = evt.name
        recipe.effort = evt.effort
        recipe.category = evt.category
        recipe.nutrition = evt.nutrition
        recipe.preparationTime = evt.preparationTime
        recipe.restTime = evt.restTime
        recipe.ingredients = evt.ingredients
        recipe.preparations = evt.preparations
        recipe.testField = evt.testField
        println(recipe)
        recipeRepository.save(recipe)
    }

    @EventHandler
    fun handle(evt: RecipeDeletedEvent) {
        recipeRepository.deleteByRecipeId(evt.recipeId)
        var imageFile = evt.image.imageId + "." + evt.image.extension
        recipeImageStore.deleteFile(imageFile)
    }

}