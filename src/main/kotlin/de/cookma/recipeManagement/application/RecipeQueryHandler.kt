package de.cookma.recipeManagement.application

import de.cookma.recipeManagement.application.queryModel.RecipeFindAllQuery
import de.cookma.recipeManagement.application.queryModel.RecipeFindQuery
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
    fun handle(query: RecipeFindQuery): RecipeViewModel {
        return recipeRepository.findByRecipeId(query.id)
    }

    @QueryHandler
    fun handle(query: RecipeFindAllQuery): List<RecipeViewModel> {
        return recipeRepository.findAll()


    }
}
