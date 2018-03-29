package io.cookma.usermanagement.infrastructure.repository

import io.cookma.usermanagement.application.UserProfileViewModel
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.query.Param

interface UserProfileRepository : MongoRepository<UserProfileViewModel, Long> {

    fun findByUsername(@Param("username") username: String): UserProfileViewModel
    fun findByUserId(@Param("userId") userId: String): UserProfileViewModel
    fun findByEmail(@Param("email") name: String): UserProfileViewModel
    fun deleteByUserId(@Param("userId") userId: String)
}