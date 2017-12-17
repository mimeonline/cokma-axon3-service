package de.cookma.recipeManagement.application

import de.cookma.recipeManagement.infrastructure.repository.RecipeRepository
import de.cookma.recipeManagement.application.queryModel.RecipeQuery
import de.cookma.recipeManagement.application.viewModel.RecipeViewModel
import org.axonframework.queryhandling.QueryHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class RecipeQueryHandler {

    @Autowired
    lateinit var recipeRepository: RecipeRepository

    @QueryHandler
    fun handle(query: RecipeQuery): RecipeViewModel {
        return recipeRepository.findByRecipeId(query.id)
    }
}
