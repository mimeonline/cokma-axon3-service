package de.cookma.recipeManagement.application

import de.cookma.recipeManagement.domain.model.RecipeCreatedEvent
import de.cookma.recipeManagement.infrastructure.repository.RecipeRepository
import de.cookma.recipeManagement.application.viewModel.RecipeViewModel
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
        recipeRepository.save(RecipeViewModel(null, evt.recipeId,evt.title,evt.subTitle,evt.preparation))
    }

}