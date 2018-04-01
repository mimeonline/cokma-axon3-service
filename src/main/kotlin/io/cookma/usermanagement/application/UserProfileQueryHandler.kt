package io.cookma.usermanagement.application

import io.cookma.usermanagement.infrastructure.repository.UserProfileRepository
import org.axonframework.queryhandling.QueryHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UserProfileQueryHandler {

    @Autowired
    lateinit var userProfileRepository: UserProfileRepository

    @QueryHandler
    fun handle(query: UserProfileFindQueryById): UserProfileProjection {
        var model: UserProfileProjection = userProfileRepository.findByUserId(query.id)
        return model
    }

    @QueryHandler
    fun handle(query: UserProfileFindAllQuery): List<UserProfileProjection> {
        var models: List<UserProfileProjection> = userProfileRepository.findAll()
        return models
    }
}