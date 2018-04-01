package io.cookma.usermanagement.infrastructure.repository

import io.cookma.usermanagement.application.UserProfileProjection
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.query.Param

interface UserProfileRepository : MongoRepository<UserProfileProjection, Long> {

    fun findByUsername(@Param("username") username: String): UserProfileProjection
    fun findByUserId(@Param("userId") userId: String): UserProfileProjection
    fun findByEmail(@Param("email") name: String): UserProfileProjection
    fun deleteByUserId(@Param("userId") userId: String)
}