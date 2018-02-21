package de.cookma.usermanagement.domain.model

import org.axonframework.commandhandling.CommandHandler
import org.axonframework.commandhandling.model.AggregateIdentifier
import org.axonframework.commandhandling.model.AggregateLifecycle
import org.axonframework.commandhandling.model.AggregateLifecycle.apply
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.spring.stereotype.Aggregate
import java.time.LocalDateTime

@Aggregate
class UserProfile {

    @AggregateIdentifier
    var userId: String? = null
    var creationDate: LocalDateTime? = null
    var username: String = ""
    var email: String = ""
    var firstname: String = ""
    var lastname: String = ""

    constructor()


    @CommandHandler
    constructor(cmd: CreateUserProfileCommand) {
        apply(UserProfileCreatedEvent(
                cmd.userId,
                LocalDateTime.now(),
                cmd.username,
                cmd.email,
                cmd.firstname,
                cmd.lastname
        ))
    }

    @CommandHandler
    fun handle(cmd: DeleteUserProfileCommand) {
        println("Delete Command " + cmd)
        apply(UserProfileDeletedEvent(cmd.userId))
    }

    @EventSourcingHandler
    fun on(evt: UserProfileCreatedEvent) {
        userId = evt.userId
        username = evt.username
        email = evt.email
        firstname = evt.firstname
        lastname = evt.lastname
    }
    @EventSourcingHandler
    fun on(evt: UserProfileDeletedEvent) {
        println("Delete Event: " + evt)
        AggregateLifecycle.markDeleted()
    }
}