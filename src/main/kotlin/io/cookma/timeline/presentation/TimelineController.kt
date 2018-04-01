package io.cookma.timeline.presentation

import io.cookma.timeline.application.TimelineApplicationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/timeline")
@CrossOrigin
class TimelineController {

    @Autowired
    lateinit var timelineApplicationService: TimelineApplicationService

    @GetMapping("/recipes")
    fun getAllRecipes() = timelineApplicationService.findAllRecipes()

}