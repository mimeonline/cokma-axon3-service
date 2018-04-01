package io.cookma.timeline.application

import io.cookma.recipeManagement.infrastructure.repository.RecipeRepository
import io.cookma.timeline.infrastructure.TimelineRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TimelineApplicationService {

    @Autowired
    lateinit var recipeRepository: RecipeRepository

    @Autowired
    lateinit var timelineRepository: TimelineRepository

    fun findAllRecipes() = timelineRepository.findAllByOrderByLastModificationDateDesc()

}