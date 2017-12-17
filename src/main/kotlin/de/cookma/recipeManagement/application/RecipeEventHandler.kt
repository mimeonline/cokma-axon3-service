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
        recipeRepository.save(RecipeViewModel(null, evt.recipeId, evt.title, evt.subTitle, evt.preparation))
    }

    @EventHandler
    fun handle(evt: RecipeUpdateEvent) {
        println(evt)
        var recipe: RecipeViewModel = recipeRepository.findByRecipeId(evt.recipeId)
        recipe.title = evt.title
        recipe.subTitle = evt.subTitle
        recipe.preparation = evt.preparation
        println(recipe)
        recipeRepository.save(recipe)
    }

    @EventHandler
    fun handle(evt: RecipeDeletedEvent) {
        recipeRepository.deleteByRecipeId(evt.recipeId)
    }

}