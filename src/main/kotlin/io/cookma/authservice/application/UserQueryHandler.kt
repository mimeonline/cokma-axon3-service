package io.cookma.authservice.application

import io.cookma.authservice.infrastructure.UserRepository
import org.axonframework.queryhandling.QueryHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UserQueryHandler {

    @Autowired
    lateinit var userRepositpory: UserRepository

    @QueryHandler
    fun handle(query: UserFindQueryById): UserProjection {
        return userRepositpory.findByUserId(query.id)
    }


    @QueryHandler
    fun handle(query: UserFindQueryByProfileId): UserProjection {
        return userRepositpory.findByUserProfileId(query.id)
    }


}