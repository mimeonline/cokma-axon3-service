package de.cookma.recipeManagement.application

import de.cookma.recipeManagement.application.dtoModel.RecipeDto
import de.cookma.recipeManagement.application.queryModel.RecipeFindAllQuery
import de.cookma.recipeManagement.application.queryModel.RecipeFindQuery
import de.cookma.recipeManagement.application.viewModel.RecipeViewModel
import de.cookma.recipeManagement.domain.model.CreateRecipeCommand
import de.cookma.recipeManagement.domain.model.DeleteRecipeCommand
import de.cookma.recipeManagement.domain.model.UpdateRecipeCommand
import de.cookma.recipeManagement.domain.model.createRecipeId
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.queryhandling.QueryGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture

@Service
class RecipeApplicationService {

    @Autowired
    lateinit var commandGateway: CommandGateway

    @Autowired
    lateinit var queryGateway: QueryGateway

    fun findRecipe(id: String): CompletableFuture<RecipeViewModel> {
        println(id)
        return queryGateway.send(RecipeFindQuery(id), RecipeViewModel::class.java)
    }

    /**
     * TODO ich würde hier gerne einen typisierten Wert zurück geben. Mir ist aber nicht klar wie ich List::class.java
     * typisieren kann
     */
    fun findAllRecipe(): CompletableFuture<List<*>>? {
        return queryGateway.send(RecipeFindAllQuery(), List::class.java)
    }
    fun createRecipe(recipe: RecipeDto): CompletableFuture<CreateRecipeCommand> {
        return commandGateway.send<CreateRecipeCommand>(
                CreateRecipeCommand(
                        createRecipeId().id,
                        recipe.title,
                        recipe.subTitle,
                        recipe.preparation))
    }

    fun updateRecipe(id: String, recipe: RecipeDto): CompletableFuture<UpdateRecipeCommand> {
        return commandGateway.send<UpdateRecipeCommand>(
                UpdateRecipeCommand(
                        id,
                        recipe.title,
                        recipe.subTitle,
                        recipe.preparation))
    }

    fun deleteRecipe(id: String) {
        commandGateway.send<DeleteRecipeCommand>(DeleteRecipeCommand(id))
    }
}