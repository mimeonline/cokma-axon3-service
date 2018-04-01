package io.cookma.authservice.infrastructure

import io.cookma.authservice.application.UserProjection
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.query.Param

interface UserRepository : MongoRepository<UserProjection, Long> {

    fun findByUserId(@Param("userId") userId: String): UserProjection
    fun findByUserProfileId(@Param("userProfileId") userProfileId: String): UserProjection
    fun findByUsername(@Param("username") username: String): UserProjection
}