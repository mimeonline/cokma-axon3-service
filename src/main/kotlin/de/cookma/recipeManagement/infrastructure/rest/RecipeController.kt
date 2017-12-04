package de.cookma.recipeManagement.infrastructure.rest

import de.cookma.recipeManagement.domain.model.Recipe
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class RecipeController {

    @GetMapping("/recipe")
    fun getRecipe() : Recipe {
        var recipe = Recipe()
        recipe.title = "Kürbis Schmaus"
        return recipe
    }
}