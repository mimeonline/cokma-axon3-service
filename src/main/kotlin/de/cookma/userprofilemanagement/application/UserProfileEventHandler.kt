package de.cookma.userprofilemanagement.application

import de.cookma.userprofilemanagement.domain.model.UserProfileCreatedEvent
import de.cookma.userprofilemanagement.domain.model.UserProfileDeletedEvent
import de.cookma.userprofilemanagement.infrastructure.repository.UserProfileRepository
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
                evt.nickname,
                evt.firstname,
                evt.lastname
        ))
    }

    @EventHandler
    fun handle(evt: UserProfileDeletedEvent) {
        userProfileRepository.deleteByUserId(evt.userId)
    }
}