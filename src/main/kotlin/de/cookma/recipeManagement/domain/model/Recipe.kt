package de.cookma.recipeManagement.domain.model

import org.axonframework.commandhandling.CommandHandler
import org.axonframework.commandhandling.model.AggregateIdentifier
import org.axonframework.commandhandling.model.AggregateLifecycle.apply
import org.axonframework.commandhandling.model.AggregateLifecycle.markDeleted
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.spring.stereotype.Aggregate

// TODO Logger benutzen!
//private val logger = KotlinLogging.logger {}

@Aggregate
class Recipe {

    @AggregateIdentifier
            //var recipeId: RecipeId? = null TODO Query Handler spielt noch nicht mit einem Nicht String zusammen
    var recipeId: String? = null
    var name: String = ""
    var image: Image = Image("", "")
    var effort: String = ""
    var category: String = ""
    var nutrition: List<String> = listOf()
    var preparationTime: Int = 0
    var restTime: Int = 0
    var ingredients: List<Ingredient> = listOf()
    var preparation: String = ""

    constructor()

    @CommandHandler
    constructor(cmd: CreateRecipeCommand) {
        println(cmd)
        apply(
            RecipeCreatedEvent(
                    cmd.recipeId,
                    cmd.name,
                    EvtImage(cmd.image.imageId, cmd.image.extension),
                    cmd.effort,
                    cmd.category,
                    cmd.nutrition,
                    cmd.preparationTime,
                    cmd.restTime,
                    cmd.ingredients,
                    cmd.preparation)
        )
    }

    @CommandHandler
    fun handle(cmd: UpdateRecipeCommand) {
        apply(
            RecipeUpdateEvent(
                    cmd.recipeId,
                    cmd.name,
                    cmd.effort,
                    cmd.category,
                    cmd.nutrition,
                    cmd.preparationTime,
                    cmd.restTime,
                    cmd.ingredients,
                    cmd.preparation))
    }

    @CommandHandler
    fun handle(cmd: DeleteRecipeCommand) {
        println("Delete Command " + cmd)
        apply(RecipeDeletedEvent(cmd.recipeId, EvtImage(image.imageId, image.extension)))
    }

    @EventSourcingHandler
    fun on(evt: RecipeCreatedEvent) {
        println(evt)
        recipeId = evt.recipeId
        name = evt.name
        image = Image(evt.image.imageId, evt.image.extension)
        effort = evt.effort
        category = evt.category
        nutrition = evt.nutrition
        preparationTime = evt.preparationTime
        restTime = evt.restTime
        ingredients = evt.ingredients
        preparation = evt.preparation
    }

    @EventSourcingHandler
    fun on(evt: RecipeUpdateEvent) {
        println(evt)
        name = evt.name
        effort = evt.effort
        category = evt.category
        nutrition = evt.nutrition
        preparationTime = evt.preparationTime
        restTime = evt.restTime
        ingredients = evt.ingredients
        preparation = evt.preparation
    }

    @EventSourcingHandler
    fun on(evt: RecipeDeletedEvent) {
        println("Delete Event: " + evt)
        markDeleted()
    }
}
