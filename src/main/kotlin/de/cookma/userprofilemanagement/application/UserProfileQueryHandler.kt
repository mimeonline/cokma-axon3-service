package de.cookma.userprofilemanagement.application

import de.cookma.userprofilemanagement.infrastructure.repository.UserProfileRepository
import org.axonframework.queryhandling.QueryHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UserProfileQueryHandler {

    @Autowired
    lateinit var userProfileRepository: UserProfileRepository

    @QueryHandler
    fun handle(query: UserProfileFindQueryById): UserProfileViewModel {
        var model: UserProfileViewModel = userProfileRepository.findByUserId(query.id)
        return model
    }

    @QueryHandler
    fun handle(query: UserProfileFindAllQuery): List<UserProfileViewModel> {
        var models: List<UserProfileViewModel> = userProfileRepository.findAll()
        return models
    }
}