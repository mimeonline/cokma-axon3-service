package de.cookma.usermanagement.application

import de.cookma.usermanagement.domain.model.UserProfileCreatedEvent
import de.cookma.usermanagement.domain.model.UserProfileDeletedEvent
import de.cookma.usermanagement.infrastructure.repository.UserProfileRepository
import org.axonframework.eventhandling.EventHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UserProfileEventHandler {

    @Autowired
    lateinit var userProfileRepository: UserProfileRepository

    @EventHandler
    fun handle(evt: UserProfileCreatedEvent) {
        userProfileRepository.save(UserProfileViewModel(
                evt.userId,
                evt.email,
                evt.username,
                evt.firstname,
                evt.lastname
        ))
    }

    @EventHandler
    fun handle(evt: UserProfileDeletedEvent) {
        userProfileRepository.deleteByUserId(evt.userId)
    }
}