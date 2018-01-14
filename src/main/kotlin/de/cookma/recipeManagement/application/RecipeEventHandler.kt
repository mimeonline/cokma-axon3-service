package de.cookma.recipeManagement.application

import de.cookma.recipeManagement.application.viewModel.RecipeViewModel
import de.cookma.recipeManagement.domain.model.RecipeCreatedEvent
import de.cookma.recipeManagement.domain.model.RecipeDeletedEvent
import de.cookma.recipeManagement.domain.model.RecipeUpdateEvent
import de.cookma.recipeManagement.infrastructure.repository.RecipeRepository
import org.axonframework.eventhandling.EventHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class RecipeEventHandler {

    @Autowired
    lateinit var recipeRepository: RecipeRepository

    @EventHandler
    fun handle(evt: RecipeCreatedEvent) {
        println(evt)
        recipeRepository.save(RecipeViewModel(
                null,
                evt.recipeId,
                evt.name,
                evt.effort,
                evt.category,
                evt.nutrition,
                evt.preparationTime,
                evt.restTime,
                evt.ingredient,
                evt.preparation))
    }

    @EventHandler
    fun handle(evt: RecipeUpdateEvent) {
        println(evt)
        var recipe: RecipeViewModel = recipeRepository.findByRecipeId(evt.recipeId)
        recipe.name = evt.name
        recipe.effort = evt.effort
        recipe.category = evt.category
        recipe.nutrition = evt.nutrition
        recipe.preparationTime = evt.preparationTime
        recipe.restTime = evt.restTime
        recipe.ingredient = evt.ingredient
        recipe.preparation = evt.preparation
        println(recipe)
        recipeRepository.save(recipe)
    }

    @EventHandler
    fun handle(evt: RecipeDeletedEvent) {
        recipeRepository.deleteByRecipeId(evt.recipeId)
    }

}