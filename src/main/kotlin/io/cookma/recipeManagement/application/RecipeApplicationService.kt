package io.cookma.recipeManagement.application

import io.cookma.recipeManagement.application.dtoModel.RecipeDto
import io.cookma.recipeManagement.application.dtoModel.RecipeEditDto
import io.cookma.recipeManagement.application.queryModel.RecipeFindAllQuery
import io.cookma.recipeManagement.application.queryModel.RecipeFindQueryById
import io.cookma.recipeManagement.application.viewModel.RecipeViewModel
import io.cookma.recipeManagement.domain.model.*
import io.cookma.recipeManagement.infrastructure.store.RecipeImageStore
import io.cookma.recipeManagement.utility.DataUri
import io.cookma.usermanagement.infrastructure.repository.UserProfileRepository
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
    lateinit var userProfileRepository: UserProfileRepository

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
        val image = storeImage(recipe)

        val userProfile = userProfileRepository.findByEmail(recipe.user)

        return   commandGateway.send<CreateRecipeCommand>(
                CreateRecipeCommand(
                        createRecipeId().id,
                        userProfile.userId,
                        recipe.name,
                        image,
                        recipe.effort,
                        recipe.category,
                        recipe.nutrition,
                        recipe.preparationTime,
                        recipe.restTime,
                        recipe.ingredients,
                        recipe.preparations))
    }

    private fun storeImage(recipe: RecipeDto): CmdImage {
        val imageId = UUID.randomUUID().toString()
        val extension = DataUri(recipe.image).extension()
        val image = CmdImage(imageId, extension)
        recipeImageStore.store(imageId, recipe.image)
        return image
    }

    fun updateRecipe(id: String, recipe: RecipeEditDto): CompletableFuture<UpdateRecipeCommand> {
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
                        recipe.preparations))
    }

    fun deleteRecipe(id: String) {
        println("Delete Recipe with ID:" + id)
        commandGateway.send<DeleteRecipeCommand>(DeleteRecipeCommand(id))
    }
}