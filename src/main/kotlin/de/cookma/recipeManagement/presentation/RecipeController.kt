package de.cookma.recipeManagement.presentation

import de.cookma.recipeManagement.application.RecipeApplicationService
import de.cookma.recipeManagement.application.dtoModel.RecipeDto
import de.cookma.recipeManagement.application.viewModel.RecipeViewModel
import de.cookma.recipeManagement.domain.model.CreateRecipeCommand
import de.cookma.recipeManagement.domain.model.UpdateRecipeCommand
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.concurrent.CompletableFuture


@RestController
@RequestMapping("/recipes")
@CrossOrigin
class RecipeController {

    @Autowired
    lateinit var recipeApplicationService: RecipeApplicationService

    @GetMapping
    fun getRecipe(): CompletableFuture<List<*>>? {
        return recipeApplicationService.findAllRecipe()
    }

    @GetMapping("/{id}")
    fun getRecipe(@PathVariable("id") id: String): CompletableFuture<RecipeViewModel> {
        println(id)
        return recipeApplicationService.findRecipe(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createRecipe(@RequestBody recipe: RecipeDto): CompletableFuture<CreateRecipeCommand> {
        return recipeApplicationService.createRecipe(recipe)
    }

    @PutMapping("/{id}")
    fun updateRecipe(
            @RequestBody recipe: RecipeDto,
            @PathVariable("id") id: String): CompletableFuture<UpdateRecipeCommand> {
        return recipeApplicationService.updateRecipe(id, recipe)
    }

    @DeleteMapping("/{id}")
    fun deleteRecipe(@PathVariable("id") id: String) {
        recipeApplicationService.deleteRecipe(id)
    }

}
