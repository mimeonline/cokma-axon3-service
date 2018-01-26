package de.cookma.userprofilemanagement.application

import de.cookma.recipeManagement.domain.model.DeleteRecipeCommand
import de.cookma.authservice.application.UserApplicationService
import de.cookma.authservice.application.UserDto
import de.cookma.userprofilemanagement.domain.model.CreateUserProfileCommand
import de.cookma.userprofilemanagement.domain.model.DeleteUserProfileCommand
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
     */
    fun registerUser(dto: RegisterUserDto): CompletableFuture<CreateUserProfileCommand> {
        val userId = UUID.randomUUID().toString()
        userApplication.saveUser(UserDto(userId, dto.username, dto.password))
        return commandGateway.send<CreateUserProfileCommand>(CreateUserProfileCommand(
                userId,
                dto.nickname,
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