package io.cookma.recipeManagement.application

import io.cookma.recipeManagement.infrastructure.repository.RecipeRepository
import org.axonframework.queryhandling.QueryHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class RecipeQueryHandler {

    @Autowired
    lateinit var recipeRepository: RecipeRepository

    @QueryHandler
    fun handle(query: RecipeFindQueryById): RecipeProjection {
        var model: RecipeProjection = recipeRepository.findByRecipeId(query.id)
        return model
    }

    /**
     * TODO Wird zur Zeit nicht benutzt siehe Kommentar: RecipeApplicationService.findAllRecipe
     */
    @QueryHandler
    fun handle(query: RecipeFindAllQuery): List<RecipeProjection> {
        var models: List<RecipeProjection> = recipeRepository.findAllByOrderByLastModificationDateDesc()
        return models
    }
}
