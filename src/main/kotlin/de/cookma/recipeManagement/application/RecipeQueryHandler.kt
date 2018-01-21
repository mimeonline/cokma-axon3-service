package de.cookma.recipeManagement.application

import de.cookma.recipeManagement.application.queryModel.RecipeFindAllQuery
import de.cookma.recipeManagement.application.queryModel.RecipeFindQueryById
import de.cookma.recipeManagement.application.viewModel.RecipeViewModel
import de.cookma.recipeManagement.infrastructure.repository.RecipeRepository
import org.axonframework.queryhandling.QueryHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class RecipeQueryHandler {

    @Autowired
    lateinit var recipeRepository: RecipeRepository

    @QueryHandler
    fun handle(query: RecipeFindQueryById): RecipeViewModel {
        var model: RecipeViewModel = recipeRepository.findByRecipeId(query.id)
        model.imageUrl = "/recipes/files/image.png"
        return model
    }

    @QueryHandler
    fun handle(query: RecipeFindAllQuery): List<RecipeViewModel> {
        var models: List<RecipeViewModel> = recipeRepository.findAll()
        models.forEach { m -> m.imageUrl = "/recipes/files/image.png" }
        return models


    }
}
