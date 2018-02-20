package de.cookma.usermanagement.application

import de.cookma.recipeManagement.domain.model.DeleteRecipeCommand
import de.cookma.authservice.application.UserApplicationService
import de.cookma.authservice.application.UserDto
import de.cookma.usermanagement.domain.model.CreateUserProfileCommand
import de.cookma.usermanagement.domain.model.DeleteUserProfileCommand
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

    fun findUserProfileById(id: String): CompletableFuture<UserProfileViewModel> {
        println(id)
        return queryGateway.send(UserProfileFindQueryById(id), UserProfileViewModel::class.java)
    }

    fun findAllUserProfiles(): CompletableFuture<List<*>>? {
        return queryGateway.send(UserProfileFindAllQuery(), List::class.java)
    }

    fun deleteUserProfile(id: String) {
        commandGateway.send<DeleteRecipeCommand>(DeleteUserProfileCommand(id))
    }
}