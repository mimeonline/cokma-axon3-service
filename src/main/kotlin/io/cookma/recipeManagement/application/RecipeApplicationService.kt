package io.cookma.recipeManagement.application

import io.cookma.imagemanagement.application.ImageApplicationService
import io.cookma.recipeManagement.domain.model.*
import io.cookma.recipeManagement.infrastructure.repository.RecipeRepository
import io.cookma.imagemanagement.infrastructure.store.ImageStore
import io.cookma.imagemanagement.utility.DataUri
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
    lateinit var imageApplicationService: ImageApplicationService

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
        val image = imageApplicationService.storeImage(recipe)

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