package io.cookma.recipeManagement.domain.model

import org.axonframework.commandhandling.CommandHandler
import org.axonframework.commandhandling.model.AggregateIdentifier
import org.axonframework.commandhandling.model.AggregateLifecycle.apply
import org.axonframework.commandhandling.model.AggregateLifecycle.markDeleted
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.spring.stereotype.Aggregate
import java.time.LocalDateTime

// TODO Logger benutzen!
//private val logger = KotlinLogging.logger {}

@Aggregate
class Recipe {

    @AggregateIdentifier
            //var recipeId: RecipeId? = null TODO Query Handler spielt noch nicht mit einem Nicht String zusammen
    var recipeId: String? = null
    var creationDate: LocalDateTime? = null
    var userProfileId = ""
    var name: String = ""
    var image: Image = Image("", "")
    var effort: String = ""
    var category: String = ""
    var nutrition: List<String> = listOf()
    var preparationTime: Int = 0
    var restTime: Int = 0
    var ingredients: List<Ingredient> = listOf()
    var preparations: List<Preparation> = listOf()
    var testField: String = ""

    constructor()

    @CommandHandler
    constructor(cmd: CreateRecipeCommand) {
        apply(
            RecipeCreatedEvent(
                    cmd.recipeId,
                    LocalDateTime.now(),
                    cmd.userProfileId,
                    cmd.name,
                    EvtImage(cmd.image.imageId, cmd.image.extension),
                    cmd.effort,
                    cmd.category,
                    cmd.nutrition,
                    cmd.preparationTime,
                    cmd.restTime,
                    cmd.ingredients,
                    cmd.preparations,
                    cmd.testField)
        )
    }

    @CommandHandler
    fun handle(cmd: UpdateRecipeCommand) {
        apply(
            RecipeUpdatedEvent(
                    cmd.recipeId,
                    cmd.name,
                    cmd.effort,
                    cmd.category,
                    cmd.nutrition,
                    cmd.preparationTime,
                    cmd.restTime,
                    cmd.ingredients,
                    cmd.preparations,
                    cmd.testField))
    }

    @CommandHandler
    fun handle(cmd: DeleteRecipeCommand) {
        apply(RecipeDeletedEvent(cmd.recipeId, EvtImage(image.imageId, image.extension)))
    }

    @EventSourcingHandler
    fun on(evt: RecipeCreatedEvent) {
        recipeId = evt.recipeId
        userProfileId = evt.userProfileId
        name = evt.name
        image = Image(evt.image.imageId, evt.image.extension)
        effort = evt.effort
        category = evt.category
        nutrition = evt.nutrition
        preparationTime = evt.preparationTime
        restTime = evt.restTime
        ingredients = evt.ingredients
        preparations = evt.preparations
        testField = evt.testField
    }

    @EventSourcingHandler
    fun on(evt: RecipeUpdatedEvent) {
        println(evt)
        println("RecipeUpdatedEvent testField=" + evt.testField)
        name = evt.name
        effort = evt.effort
        category = evt.category
        nutrition = evt.nutrition
        preparationTime = evt.preparationTime
        restTime = evt.restTime
        ingredients = evt.ingredients
        preparations = evt.preparations
        testField = evt.testField
    }

    @EventSourcingHandler
    fun on(evt: RecipeDeletedEvent) {
        println(evt)
        markDeleted()
    }
}
