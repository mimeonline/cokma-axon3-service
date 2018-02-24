package io.cookma.authservice.application

import io.cookma.authservice.domain.model.UserCreatedEvent
import io.cookma.authservice.infrastructure.UserRepository
import org.axonframework.eventhandling.EventHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UserEventHandler {

    @Autowired
    lateinit var userRepository: UserRepository

    @EventHandler
    fun handle(evt: UserCreatedEvent) {
        userRepository.save(UserViewModel(
                evt.userId,
                evt.userProfileId,
                evt.username,
                evt.password
        ))
    }

}