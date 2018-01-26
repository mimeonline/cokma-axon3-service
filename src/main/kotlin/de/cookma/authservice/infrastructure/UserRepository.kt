package de.cookma.authservice.infrastructure

import de.cookma.authservice.application.UserViewModel
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.query.Param

interface UserRepository : MongoRepository<UserViewModel, Long> {

    fun findByUserId(@Param("userId") userId: String): UserViewModel
    fun findByUserProfileId(@Param("userProfileId") userProfileId: String): UserViewModel
}