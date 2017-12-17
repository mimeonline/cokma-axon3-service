package de.cookma.recipeManagement.infrastructure.config

import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore
import org.axonframework.eventsourcing.eventstore.EventStorageEngine
import org.axonframework.eventsourcing.eventstore.EventStore
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class AxomConfiguration {

    @Bean
    fun eventStorageEngine(): EventStorageEngine {
        return InMemoryEventStorageEngine()
    }

    @Bean
    fun eventStore(): EventStore {
        return EmbeddedEventStore(eventStorageEngine())
    }

//    @Bean
//    fun recipeRepository(): Repository<Recipe> {
//        return EventSourcingRepository(Recipe::class.java, eventStore())
//    }

}
