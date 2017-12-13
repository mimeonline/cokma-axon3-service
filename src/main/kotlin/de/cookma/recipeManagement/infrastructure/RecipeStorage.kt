package de.cookma.recipeManagement.infrastructure

import de.cookma.recipeManagement.domain.model.Recipe
import de.cookma.recipeManagement.domain.model.RecipeCreatedEvent
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Service

@Service
class RecipeStorage {

    var storage: Map<String, Recipe> = HashMap<String, Recipe>()

    @EventHandler
    fun handle(event : RecipeCreatedEvent) {
        event.recipeId
    }

    fun storeRecipe() {

    }
}
