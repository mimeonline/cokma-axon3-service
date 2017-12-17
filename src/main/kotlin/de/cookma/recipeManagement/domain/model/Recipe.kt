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
    var title: String = ""
    var subTitle: String = ""
    //    var images: List<Image> = ArrayList() TODO Komplexer Datentyp, wie integriert man den?
    //    var ingredients: List<Ingredient> = ArrayList()
    var preparation: String = ""

    constructor()

    @CommandHandler
    constructor(cmd: CreateRecipeCommand) {
        println(cmd)
        AggregateLifecycle.apply(RecipeCreatedEvent(cmd.recipeId, cmd.title, cmd.subTitle, cmd.preparation))
    }

    @CommandHandler
    fun handle(cmd: UpdateRecipeCommand) {
        AggregateLifecycle.apply(RecipeUpdateEvent(cmd.recipeId, cmd.title, cmd.subTitle, cmd.preparation))
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
        title = evt.title
        subTitle = evt.subTitle
        preparation = evt.preparation
    }

    @EventSourcingHandler
    fun on(evt: RecipeUpdateEvent) {
        println(evt)
        title = evt.title
        subTitle = evt.subTitle
        preparation = evt.preparation
    }

    @EventSourcingHandler
    fun on(evt: RecipeDeletedEvent) {
        println("Delete Event: " + evt)
        markDeleted()
    }
}
