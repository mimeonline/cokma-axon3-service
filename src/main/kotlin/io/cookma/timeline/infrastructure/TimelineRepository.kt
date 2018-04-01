package io.cookma.timeline.infrastructure

import io.cookma.timeline.application.TimelineRecipeProjection
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.query.Param

interface TimelineRepository : MongoRepository<TimelineRecipeProjection, Long> {

    fun findByRecipeId(@Param("recipeId") recipeId: String): TimelineRecipeProjection
    fun deleteByRecipeId(@Param("recipeId") recipeId: String)
    fun findAllByOrderByLastModificationDateDesc(): List<TimelineRecipeProjection>
}