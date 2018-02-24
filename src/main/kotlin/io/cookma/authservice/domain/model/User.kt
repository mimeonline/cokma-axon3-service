package io.cookma.authservice.domain.model

import org.axonframework.commandhandling.CommandHandler
import org.axonframework.commandhandling.model.AggregateIdentifier
import org.axonframework.commandhandling.model.AggregateLifecycle.apply
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.spring.stereotype.Aggregate

@Aggregate
class User {

    @AggregateIdentifier
    var userId: String? = null
    var userProfileId: String= ""
    var username: String = ""
    var password: String = ""

    constructor()

    @CommandHandler
    constructor(cmd: CreateUserCommand) {
        // TODO Passwort verschlüsseln
        apply(UserCreatedEvent(
                cmd.userId,
                cmd.userProfileId,
                cmd.username,
                cmd.password
        ))
    }

    @EventSourcingHandler
    fun on(evt: UserCreatedEvent) {
        userId = evt.userId
        userProfileId = evt.userProfileId
        username = evt.username
        password = evt.password
    }
}