package io.cookma.recipeManagement.application

import io.cookma.recipeManagement.application.queryModel.RecipeFindAllQuery
import io.cookma.recipeManagement.application.queryModel.RecipeFindQueryById
import io.cookma.recipeManagement.application.viewModel.RecipeViewModel
import io.cookma.recipeManagement.infrastructure.repository.RecipeRepository
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
        return model
    }

    @QueryHandler
    fun handle(query: RecipeFindAllQuery): List<RecipeViewModel> {
        var models: List<RecipeViewModel> = recipeRepository.findAll()
        return models
    }
}
