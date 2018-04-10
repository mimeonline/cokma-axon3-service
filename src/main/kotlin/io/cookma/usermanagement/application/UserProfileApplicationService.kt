package io.cookma.usermanagement.application

import io.cookma.recipeManagement.domain.model.DeleteRecipeCommand
import io.cookma.authservice.application.UserApplicationService
import io.cookma.authservice.application.UserDto
import io.cookma.usermanagement.domain.model.CreateUserProfileCommand
import io.cookma.usermanagement.domain.model.DeleteUserProfileCommand
import io.cookma.usermanagement.infrastructure.repository.UserProfileRepository
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.queryhandling.QueryGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import java.util.concurrent.CompletableFuture

@Service
class UserProfileApplicationService {

    @Autowired
    lateinit var commandGateway: CommandGateway

    @Autowired
    lateinit var queryGateway: QueryGateway

    @Autowired
    lateinit var userProfileRepository: UserProfileRepository

    @Autowired
    lateinit var userApplication: UserApplicationService

    /**
     * TODO Email muß unique sein
     * - An dieser Stelle die Email Unique Prüfung auf dem View Model durchführen
     * - Das authservice Modul soll ein JWT Token zurück geben
     */
    fun registerUser(dto: RegisterUserDto): CompletableFuture<CreateUserProfileCommand> {
        val userId = UUID.randomUUID().toString()
        userApplication.saveUser(UserDto(userId, dto.email, dto.password))
        return commandGateway.send<CreateUserProfileCommand>(CreateUserProfileCommand(
                userId,
                dto.username,
                dto.email,
                dto.firstname,
                dto.lastname
        ))
    }

    fun findUserProfileById(id: String): CompletableFuture<UserProfileProjection> {
        println(id)
        return queryGateway.send(UserProfileFindQueryById(id), UserProfileProjection::class.java)
    }

    fun findAllUserProfiles():  List<UserProfileProjection> {
        return userProfileRepository.findAll()
    }

    fun deleteUserProfile(id: String) {
        commandGateway.send<DeleteRecipeCommand>(DeleteUserProfileCommand(id))
    }
}