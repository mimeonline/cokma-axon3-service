package de.cookma.recipeManagement.application

import de.cookma.recipeManagement.application.dtoModel.RecipeDto
import de.cookma.recipeManagement.application.queryModel.RecipeFindAllQuery
import de.cookma.recipeManagement.application.queryModel.RecipeFindQueryById
import de.cookma.recipeManagement.application.viewModel.RecipeViewModel
import de.cookma.recipeManagement.domain.model.*
import de.cookma.recipeManagement.infrastructure.store.RecipeImageStore
import de.cookma.recipeManagement.presentation.RecipeController
import de.cookma.recipeManagement.utility.DataUri
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.queryhandling.QueryGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import java.util.concurrent.CompletableFuture

@Service
class RecipeApplicationService {

    @Autowired
    lateinit var recipeImageStore: RecipeImageStore

    @Autowired
    lateinit var commandGateway: CommandGateway

    @Autowired
    lateinit var queryGateway: QueryGateway

    fun findRecipeById(id: String): CompletableFuture<RecipeViewModel> {
        println(id)
        return queryGateway.send(RecipeFindQueryById(id), RecipeViewModel::class.java)
    }

    /**
     * TODO ich würde hier gerne einen typisierten Wert zurück geben. Mir ist aber nicht klar wie ich List::class.java
     * typisieren kann
     */
    fun findAllRecipe(): CompletableFuture<List<*>>? {
        return queryGateway.send(RecipeFindAllQuery(), List::class.java)
    }

    fun createRecipe(recipe: RecipeDto): CompletableFuture<CreateRecipeCommand> {
        println(recipe)
        val imageId = UUID.randomUUID().toString()
        val extension = DataUri(recipe.image).extension()
        val image = CmdImage(imageId, extension)
        recipeImageStore.store(imageId, recipe.image)
        return   commandGateway.send<CreateRecipeCommand>(
                CreateRecipeCommand(
                        createRecipeId().id,
                        recipe.name,
                        image,
                        recipe.effort,
                        recipe.category,
                        recipe.nutrition,
                        recipe.preparationTime,
                        recipe.restTime,
                        recipe.ingredients,
                        recipe.preparation))
    }

    fun updateRecipe(id: String, recipe: RecipeDto): CompletableFuture<UpdateRecipeCommand> {
        return commandGateway.send<UpdateRecipeCommand>(
                UpdateRecipeCommand(
                        id,
                        recipe.name,
                        recipe.effort,
                        recipe.category,
                        recipe.nutrition,
                        recipe.preparationTime,
                        recipe.restTime,
                        recipe.ingredients,
                        recipe.preparation))
    }

    fun deleteRecipe(id: String) {
        commandGateway.send<DeleteRecipeCommand>(DeleteRecipeCommand(id))
    }
}