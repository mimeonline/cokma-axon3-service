package io.cookma.recipeManagement.presentation

import io.cookma.recipeManagement.application.RecipeApplicationService
import io.cookma.recipeManagement.application.dtoModel.RecipeDto
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
    fun getAllRecipe(): CompletableFuture<List<*>>? {
        return recipeApplicationService.findAllRecipe()
    }

    @GetMapping("/{id}")
    fun getRecipe(@PathVariable("id") id: String) = recipeApplicationService.findRecipeById(id)


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createRecipe(@RequestBody recipe: RecipeDto) = recipeApplicationService.createRecipe(recipe)


    @PutMapping("/{id}")
    fun updateRecipe(
            @RequestBody recipe: RecipeDto,
            @PathVariable("id") id: String) = recipeApplicationService.updateRecipe(id, recipe)


    @DeleteMapping("/{id}")
    fun deleteRecipe(@PathVariable("id") id: String) = recipeApplicationService.deleteRecipe(id)


}
