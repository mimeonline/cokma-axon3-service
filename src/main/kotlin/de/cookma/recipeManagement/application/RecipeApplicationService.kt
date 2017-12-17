package de.cookma.recipeManagement.application

import de.cookma.recipeManagement.application.viewModel.RecipeViewModel
import de.cookma.recipeManagement.domain.model.CreateRecipeCommand
import de.cookma.recipeManagement.domain.model.createRecipeId
import de.cookma.recipeManagement.application.dtoModel.RecipeDto
import de.cookma.recipeManagement.application.queryModel.RecipeQuery
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.queryhandling.QueryGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import java.util.concurrent.CompletableFuture

class RecipeApplicationService {

    @Autowired
    lateinit var commandGateway: CommandGateway

    @Autowired
    lateinit var queryGateway: QueryGateway

    fun getRecipe(id: String): CompletableFuture<RecipeViewModel> {
        println(id)
        return queryGateway.send(RecipeQuery(id), RecipeViewModel::class.java)
    }

    fun createRecipe(@RequestBody recipe: RecipeDto): CompletableFuture<CreateRecipeCommand> {
        return commandGateway.send<CreateRecipeCommand>(
                CreateRecipeCommand(
                        createRecipeId(),
                        recipe.title,
                        recipe.subTitle,
                        recipe.preparation))
    }
}