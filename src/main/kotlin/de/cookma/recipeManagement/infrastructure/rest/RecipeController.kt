package de.cookma.recipeManagement.infrastructure.rest

import de.cookma.recipeManagement.domain.model.CreateRecipeCommand
import de.cookma.recipeManagement.domain.model.createRecipeId
import de.cookma.recipeManagement.infrastructure.rest.model.RecipeDto
import de.cookma.recipeManagement.infrastructure.rest.model.RecipeQuery
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.queryhandling.QueryGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.concurrent.CompletableFuture


@RestController
@RequestMapping("/recipes")
class RecipeController {

    @Autowired
    lateinit var commandGateway: CommandGateway

    @Autowired
    lateinit var queryGateway: QueryGateway


    @GetMapping
    fun getRecipe() = RecipeDto("Title", "Subtitle", "preparation")

    @GetMapping("/{id}")
    fun getRecipe(@PathVariable("id") id: String): CompletableFuture<RecipeDto> {
        return queryGateway.send(RecipeQuery(id), RecipeDto::class.java)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createRecipe(@RequestBody recipe: RecipeDto): CompletableFuture<CreateRecipeCommand> {
        return commandGateway.send<CreateRecipeCommand>(
                CreateRecipeCommand(
                        createRecipeId(),
                        recipe.title,
                        recipe.subTitle,
                        recipe.preparation))
    }
}
