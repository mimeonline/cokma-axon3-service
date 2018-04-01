package io.cookma.usermanagement.application

import io.cookma.usermanagement.domain.model.UserProfileCreatedEvent
import io.cookma.usermanagement.domain.model.UserProfileDeletedEvent
import io.cookma.usermanagement.infrastructure.repository.UserProfileRepository
import org.axonframework.config.ProcessingGroup
import org.axonframework.eventhandling.EventHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
@ProcessingGroup("usermanagement")
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