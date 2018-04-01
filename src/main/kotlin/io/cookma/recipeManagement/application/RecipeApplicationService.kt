package io.cookma.recipeManagement.application

import io.cookma.recipeManagement.domain.model.*
import io.cookma.recipeManagement.infrastructure.repository.RecipeRepository
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

    @Autowired
    lateinit var recipeRepository: RecipeRepository

    fun findRecipeById(id: String): CompletableFuture<RecipeProjection> {
        return queryGateway.query(RecipeFindQueryById(id), RecipeProjection::class.java)
    }

    /**
     * TODO Der QueryBus in Axon Framework Version 3.2 hat einen Bug. Statt eines MultipleInstancesResponseType wird
     * ein InstanceResponseType instantiziert. Deshalb findet der QueryBus den Rückgabe Typ List nicht.
     * {@link ResponseTypes}
     * Deshalb gebe ich die Collection direkt vom Repository zurück
     */
    fun findAllRecipe(): List<RecipeProjection>? {
        return recipeRepository.findAllByOrderByLastModificationDateDesc()
    }

    fun createRecipe(recipe: RecipeDto): CompletableFuture<CreateRecipeCommand> {
        val image = storeImage(recipe)

        return commandGateway.send<CreateRecipeCommand>(
                CreateRecipeCommand(
                        createRecipeId().id,
                        recipe.userId,
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
        commandGateway.send<DeleteRecipeCommand>(DeleteRecipeCommand(id))
    }
}