package io.cookma.timeline.application

import io.cookma.recipeManagement.domain.model.RecipeCreatedEvent
import io.cookma.recipeManagement.domain.model.RecipeDeletedEvent
import io.cookma.recipeManagement.domain.model.RecipeUpdatedEvent
import io.cookma.timeline.infrastructure.TimelineRepository
import io.cookma.usermanagement.infrastructure.repository.UserProfileRepository
import org.axonframework.config.ProcessingGroup
import org.axonframework.eventhandling.EventHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
@ProcessingGroup("timeline")
class TimelineEventHandler {

    @Autowired
    lateinit var timelineRepository: TimelineRepository


    @Autowired
    lateinit var userProfileRepository: UserProfileRepository

    @EventHandler
    fun handle(evt: RecipeCreatedEvent) {
        val imageUrl = "/images/" + evt.image.imageId + "." + evt.image.extension
        val userProfile = userProfileRepository.findByUserId(evt.userProfileId)
        timelineRepository.save(TimelineRecipeProjection(
                null,
                evt.recipeId,
                evt.creationDate,
                evt.name,
                imageUrl,
                userProfile.username
        ))
    }

    @EventHandler
    fun handle(evt: RecipeUpdatedEvent) {
        var trp: TimelineRecipeProjection = timelineRepository.findByRecipeId(evt.recipeId)
        trp.name = evt.name
        timelineRepository.save(trp)
    }

    @EventHandler
    fun handle(evt: RecipeDeletedEvent) {
        timelineRepository.deleteByRecipeId(evt.recipeId)
    }
}