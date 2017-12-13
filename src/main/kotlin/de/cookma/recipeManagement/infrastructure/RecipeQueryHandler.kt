package de.cookma.recipeManagement.infrastructure

import de.cookma.recipeManagement.domain.model.Recipe
import de.cookma.recipeManagement.infrastructure.rest.model.RecipeDto
import de.cookma.recipeManagement.infrastructure.rest.model.RecipeQuery
import org.axonframework.commandhandling.model.Repository
import org.axonframework.queryhandling.QueryHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class RecipeQueryHandler {

    @Autowired
    lateinit var repository: Repository<Recipe>

    @QueryHandler
    fun handle(query: RecipeQuery): RecipeDto {
        var aggregate = repository.load(query.id)
        return RecipeDto(
                aggregate.invoke(Recipe::title),
                aggregate.invoke(Recipe::subTitle),
                aggregate.invoke(Recipe::preparation))
    }
}
