package de.cookma.authservice.application

import de.cookma.authservice.domain.model.CreateUserCommand
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.queryhandling.QueryGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import java.util.concurrent.CompletableFuture

@Service
class UserApplicationService {

    @Autowired
    lateinit var commandGateway: CommandGateway

    @Autowired
    lateinit var queryGateway: QueryGateway

    /**
     * TODO Password verschlüsseln
     */
    fun saveUser(dto: UserDto): CompletableFuture<CreateUserCommand> {
        return commandGateway.send<CreateUserCommand>(CreateUserCommand(
                UUID.randomUUID().toString(),
                dto.userProfileId,
                dto.username,
                dto.password
        ))
    }

    fun findUserById(id: String): CompletableFuture<UserViewModel> {
        return queryGateway.send(UserFindQueryById(id), UserViewModel::class.java)
    }

    fun findUserProfileById(id: String): CompletableFuture<UserViewModel> {
        return queryGateway.send(UserFindQueryByProfileId(id), UserViewModel::class.java)
    }
}