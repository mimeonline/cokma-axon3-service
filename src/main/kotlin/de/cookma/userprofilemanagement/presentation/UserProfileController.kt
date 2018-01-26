package de.cookma.userprofilemanagement.presentation

import de.cookma.userprofilemanagement.application.RegisterUserDto
import de.cookma.userprofilemanagement.application.UserProfileApplicationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
@CrossOrigin
class UserProfileController {

    @Autowired
    lateinit var userProfileApplicationService: UserProfileApplicationService

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    fun registerUser(@RequestBody registerUser: RegisterUserDto) =
            userProfileApplicationService.registerUser(registerUser)


    @GetMapping("/{userid}")
    fun findUse(@PathVariable("userid") userId: String) =
            userProfileApplicationService.findUserProfileById(userId)


    @RequestMapping
    fun findUsers() =
            userProfileApplicationService.findAllUserProfiles()


    @DeleteMapping("/{id}")
    fun deleteRecipe(@PathVariable("id") id: String) =
            userProfileApplicationService.deleteUserProfile(id)
}