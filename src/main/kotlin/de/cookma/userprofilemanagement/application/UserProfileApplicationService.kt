package de.cookma.userprofilemanagement.application

import de.cookma.recipeManagement.application.queryModel.RecipeFindQueryById
import de.cookma.recipeManagement.application.viewModel.RecipeViewModel
import de.cookma.recipeManagement.infrastructure.store.RecipeImageStore
import de.cookma.userprofilemanagement.infrastructure.repository.UserProfileRepository
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.queryhandling.QueryGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture

@Service
class UserProfileApplicationService {

    @Autowired
    lateinit var userProfileRepository: UserProfileRepository

    @Autowired
    lateinit var commandGateway: CommandGateway

    @Autowired
    lateinit var queryGateway: QueryGateway

    fun registerUser(registerUserDto: RegisterUserDto) {
        // do something
    }

    fun findUserById(id: String): CompletableFuture<UserProfileViewModel> {
        println(id)
        return queryGateway.send(RecipeFindQueryById(id), UserProfileViewModel::class.java)
    }
}