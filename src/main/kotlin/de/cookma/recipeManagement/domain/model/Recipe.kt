package de.cookma.recipeManagement.domain.model

import mu.KotlinLogging
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.commandhandling.model.AggregateIdentifier
import org.axonframework.commandhandling.model.AggregateLifecycle
import org.axonframework.commandhandling.model.AggregateLifecycle.markDeleted
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.spring.stereotype.Aggregate

// TODO Logger benutzen!
private val logger = KotlinLogging.logger {}

@Aggregate
class Recipe {

    @AggregateIdentifier
            //var recipeId: RecipeId? = null TODO Query Handler spielt noch nicht mit einem Nicht String zusammen
    var recipeId: String? = null
    var name: String = ""
    var effort: String = ""
    var category: String = ""
    var nutrition: List<String> =  listOf()
    var preparationTime: Int = 0
    var restTime: Int = 0
    var ingredient: String = ""
    //    var images: List<Image> = ArrayList() TODO Komplexer Datentyp, wie integriert man den?
    //    var ingredients: List<Ingredient> = ArrayList()
    var preparation: String = ""

    constructor()

    @CommandHandler
    constructor(cmd: CreateRecipeCommand) {
        println(cmd)
        AggregateLifecycle.apply(
                RecipeCreatedEvent(
                        cmd.recipeId,
                        cmd.name,
                        cmd.effort,
                        cmd.category,
                        cmd.nutrition,
                        cmd.preparationTime,
                        cmd.restTime,
                        cmd.ingredient,
                        cmd.preparation)
        )
    }

    @CommandHandler
    fun handle(cmd: UpdateRecipeCommand) {
        AggregateLifecycle.apply(
                RecipeUpdateEvent(
                        cmd.recipeId,
                        cmd.name,
                        cmd.effort,
                        cmd.category,
                        cmd.nutrition,
                        cmd.preparationTime,
                        cmd.restTime,
                        cmd.ingredient,
                        cmd.preparation))
    }

    @CommandHandler
    fun handle(cmd: DeleteRecipeCommand) {
        println("Delete Command " + cmd)
        AggregateLifecycle.apply(RecipeDeletedEvent(cmd.recipeId))
    }

    @EventSourcingHandler
    fun on(evt: RecipeCreatedEvent) {
        println(evt)
        recipeId = evt.recipeId
        name = evt.name
        effort = evt.effort
        category = evt.category
        nutrition = evt.nutrition
        preparationTime = evt.preparationTime
        restTime = evt.restTime
        ingredient = evt.ingredient
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
        ingredient = evt.ingredient
        preparation = evt.preparation
    }

    @EventSourcingHandler
    fun on(evt: RecipeDeletedEvent) {
        println("Delete Event: " + evt)
        markDeleted()
    }
}
