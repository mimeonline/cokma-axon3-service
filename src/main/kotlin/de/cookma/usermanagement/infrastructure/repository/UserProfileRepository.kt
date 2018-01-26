package de.cookma.usermanagement.infrastructure.repository

import de.cookma.usermanagement.application.UserProfileViewModel
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.query.Param

interface UserProfileRepository : MongoRepository<UserProfileViewModel, Long> {

    fun findByNickname(@Param("nickname") name: String): UserProfileViewModel
    fun findByUserId(@Param("userId") recipeId: String): UserProfileViewModel
    fun findByEmail(@Param("email") name: String): UserProfileViewModel
    fun deleteByUserId(@Param("userId") userId: String)
}